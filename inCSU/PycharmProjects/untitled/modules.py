
import random
import time
import pickle
import settings
import os


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

    def decrease(self, cost):
        """
        减少资产
        :param cost: 减少的数量
        :return:
        """
        self.__assets -= cost


class Course:
    """
    课程相关信息
    """

    def __init__(self, course_name, cost, teacher_obj, admin):
        self.course_name = course_name
        self.cost = cost
        self.teacher = teacher_obj
        self.create_time = time.strftime('%Y-%m-%d %H:%M:%S')
        self.create_admin = admin

    def have_lesson(self):
        """
        课程上课，自动给相关联的任课老师增加课时费
        :return: 课程内容返回给上课者
        """
        self.teacher.gain(self.cost)

        content = random.randrange(10, 100)
        r = time.strftime('%Y-%m-%d %H:%M:%S')
        temp = "课程：%s；老师：%s；内容：%d；时间：%f" % (self.course_name, self.teacher, content, r)
        return temp

    def absence(self):
        """
        教学事故
        :return:
        """
        self.teacher.decrease(self.cost * 2)


class Admin:
    def __init__(self):
        self.username = None
        self.password = None

    def login(self, user, pwd):
        """
        管理员登陆
        :param user:
        :param pwd:
        :return:
        """
        if self.username == user and self.password == pwd:
            return True
        else:
            return False

    def register(self, user, pwd):
        """
        管理员注册
        :param user:
        :param pwd:
        :return:
        """
        self.username = user
        self.password = pwd

        path = os.path.join(settings.BASE_ADMIN_DIR, self.username)  # 管理员目录
        pickle.dump(self, open(path, 'xb'))  # 将管理员对象写入文件


class Student:
    """
    学生相关信息
    """

    def __init__(self):
        self.username = None
        self.password = None

        self.course_list = []
        self.study_dict = {}

    def select_course(self, course_obj):
        """
        学生选课
        :param course_obj:
        :return:
        """
        self.course_list.append(course_obj)  # 将课程对象添加进课程列表

    def study(self, course_obj):
        """
        学生上课
        :param course_obj:
        :return:
        """
        class_result = course_obj.have_lesson()  # 获取学生上课信息

        if course_obj in self.study_dict.keys():  # key：课程对象 value：上课信息列表，是列表格式

            self.study_dict[course_obj].append(class_result)  # 将上课信息列表添加进上一次的列表中
        else:
            self.study_dict[course_obj] = [class_result, ]  # 创建该课程对象的键值对

    def login(self, user, pwd):
        """
        学生登陆
        :param user:
        :param pwd:
        :return:
        """
        if self.username == user and self.password == pwd:
            return True
        else:
            return False

    def register(self, user, pwd):
        """
        学生注册
        :param user:
        :param pwd:
        :return:
        """
        self.username = user
        self.password = pwd

        path = os.path.join(settings.BASE_STUDENTS_DIR, self.username)  # 学生目录
        pickle.dump(self, open(path, 'xb'))  # 将学生对象写入学生目录
