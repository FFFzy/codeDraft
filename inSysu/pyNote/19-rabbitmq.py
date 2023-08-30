# base.py
import json
import datetime
import time
import random
import pika
from pika.exceptions import ChannelClosed, ConnectionClosed

# rabbitmq 配置信息
MQ_CONFIG = {
    "hostname": "127.0.0.1",
    "port": 5672,
    "vhost": "my_vhost",
    "username": "admin",
    "password": "adminxxx",
    "exchange": "my_exchange",
    "queue": "my_queue",
    "routing_key": "my_key"
}

class RabbitMQServer(object):
    def __init__(self):
        self.config = MQ_CONFIG  # 配置文件加载
        self.host = self.config.get("hostname")
        self.port = self.config.get("port")
        self.username = self.config.get("username")
        self.password = self.config.get("password")
        self.vhost = self.config.get("vhost")
        self.exchange = self.config.get("exchange")
        self.queue = self.config.get("queue")
        self.routing_key = self.config.get("routing_key")

        self.connection = None
        self.channel = None

        # 关于队列的声明，如果使用同一套参数进行声明了，就不能再使用其他参数来声明
        self.arguments = {
            'x-message-ttl': 82800000,   # 设置队列中的所有消息的生存周期
            'x-expires': 82800000,       # 当队列在指定的时间没有被访问(consume, basicGet, queueDeclare…)就会被删除
            'x-max-length': 100000,      # 限定队列的消息的最大条数，超过指定条数将会把最早的几条删除掉
            'x-max-priority': 10         # 声明队列时先定义最大优先级值，在发布消息的时候指定该消息的优先级
        }

    def reconnect(self):
        try:
            if self.connection and not self.connection.is_closed:
                self.connection.close()

            credentials = pika.PlainCredentials(self.username, self.password)
            parameters = pika.ConnectionParameters(self.host, self.port, self.vhost, credentials)
                                  
            self.connection = pika.BlockingConnection(parameters)
            self.channel = self.connection.channel()
            self.channel.exchange_declare(exchange=self.exchange, exchange_type="direct", durable=True)
            self.channel.queue_declare(queue=self.queue, exclusive=False, durable=True, arguments=self.arguments) 
            self.channel.queue_bind(exchange=self.exchange, queue=self.queue, routing_key=self.routing_key)

            if isinstance(self, RabbitComsumer):
                self.channel.basic_qos(prefetch_count=1)  # prefetch 表明最大阻塞未ack的消息数量
                self.channel.basic_consume(on_message_callback=self.consumer_callback, queue=self.queue, auto_ack=False)

        except Exception as e:
            print("RECONNECT: ", e)

# base.py            
class RabbitPublisher(RabbitMQServer):
    def __init__(self):
        super(RabbitPublisher, self).__init__()

    def start_publish(self):
        self.reconnect()
        i = 1
        while True:
            message = {"value": i}
            try:
                self.channel.basic_publish(exchange=self.exchange, routing_key=self.routing_key, body=json.dumps(message))
                print("Publish value: ", i)
                i += 1
                time.sleep(3)
            except ConnectionClosed as e:
                print("ConnectionClosed: ", e)
                self.reconnect()
                time.sleep(2)
            except ChannelClosed as e:
                print("ChannelClosed: ", e)
                self.reconnect()
                time.sleep(2)
            except Exception as e:
                print("basic_publish: ", e)
                self.reconnect()
                time.sleep(2)
  
# base.py 
class RabbitComsumer(RabbitMQServer):
    def __init__(self):
        super(RabbitComsumer, self).__init__()
    
    def execute(self, body):
        body = body.decode('utf8')
        body = json.loads(body)
        print(body["value"])
        return True

    def consumer_callback(self, channel, method, properties, body):
        result = self.execute(body)
        if channel.is_open:
            if result:
                channel.basic_ack(delivery_tag=method.delivery_tag)   # 发送ack
            else:
                channel.basic_nack(delivery_tag=method.delivery_tag, multiple=False, requeue=True)
        if not channel.is_open:
            print("Callback 接收频道关闭，无法ack")

    def start_consumer(self):
        self.reconnect()
        while True:
            try:
                self.channel.start_consuming()  #启动消息接受 进入死循环
            except ConnectionClosed as e:
                print("ConnectionClosed: ", e)
                self.reconnect()
                time.sleep(2)
            except ChannelClosed as e:
                print("ChannelClosed: ", e)
                self.reconnect()
                time.sleep(2)
            except Exception as e:
                print("consuming: ", e)
                self.reconnect()
                time.sleep(2)              

# publish.py
from base import RabbitPublisher

if __name__ == '__main__':
    publisher = RabbitPublisher()
    publisher.start_publish()

# consumer.py
from base import RabbitComsumer

if __name__ == '__main__':
    consumer = RabbitComsumer()
    consumer.start_consumer()