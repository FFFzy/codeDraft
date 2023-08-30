import os
import logging
from concurrent_log_handler import ConcurrentRotatingFileHandler


cache_logger = {}


def get_logger(log_name):
    if log_name not in cache_logger:
        path = './logs'
        if not os.path.exists(path):
            os.makedirs(path)
        log_path = os.path.join(path, log_name)

        handler = ConcurrentRotatingFileHandler(log_path, "a", 20 * 1024 * 1024, 10)
        fmt = '%(asctime)s - %(levelname)s - %(message)s'
        formatter = logging.Formatter(fmt)
        handler.setFormatter(formatter)

        logger = logging.getLogger()
        logger.addHandler(handler)
        logger.setLevel(logging.INFO)

        cache_logger[log_name] = logger
    return cache_logger[log_name]


class Logger(object):
    def __init__(self, log_name=None):
        if log_name is None:
            log_name = 'default.log'

        self.logger = get_logger(log_name)

    def info(self, msg, *args, **kwargs):
        self.logger.info(msg, *args, **kwargs)

    def error(self, msg, *args, **kwargs):
        self.logger.error(msg, *args, **kwargs)


if __name__ == '__main__':
    l = Logger()
    l.info("TEST")





