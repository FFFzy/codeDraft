import time

class Teacher:
    """
    封装老师的相关信息
    """
    def __init__(self, name, age, admin):
        self.name = name
        self.age = age
        self.__assets = 0
        self.create_time = time.strftime('%Y-%m-%d %H:%M:%S')
        self.create_admin = admin

    def gain(self, cost):
        """
        增加资产
        :param cost: 增加的数量
        :return:
        """
        self.__assets += cost
        return self.__assets

    def decrease(self, cost):
        """
        减少资产
        :param cost: 减少的数量
        :return:
        """
        self.__assets -= cost
        return self.__assets
