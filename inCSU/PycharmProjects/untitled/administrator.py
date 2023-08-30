
import os
import sys

sys.path.append(os.path.dirname(os.path.dirname(__file__)))

import pickle
import os
import models
import settings
from lib.models import *


def create_course(admin_obj):
    teacher_list = pickle.load(open(settings.TEACHER_DB_DIR, 'rb'))  # 读取老师清单
    for num, item in enumerate(teacher_list, 1):  # 打印老师清单
        print(num, item.name, item.age, item.create_time, item.create_admin.username)
    course_list = []  # 创建课程列表
    while True:
        name = input('请输入课程名称(q退出)：')
        if name == 'q':
            break
        cost = input('请输入课时费：')
        num = input('选择老师(序号)：')
        obj = models.Course(name, cost, teacher_list[int(num) - 1], admin_obj)  # 创建课程对象
        course_list.append(obj)

    if os.path.exists(settings.COURSE_DB_DIR):  # 如果有课程列表
        exists_list = pickle.load(open(settings.COURSE_DB_DIR, 'rb'))
        course_list.extend(exists_list)  # 对原有课程列表进行扩展
    pickle.dump(course_list, open(settings.COURSE_DB_DIR, 'wb'))  # 将学生列表写入文件


def create_teacher(admin_obj):
    teacher_list = []  # 创建老师列表
    while True:
        teacher_name = input('请输入老师姓名(q表示退出)')
        if teacher_name == 'q':
            break
        teacher_age = input('请输入老师年龄')
        obj = models.Teacher(teacher_name, teacher_age, admin_obj)  # 创建老师对象
        teacher_list.append(obj)
    if os.path.exists(settings.TEACHER_DB_DIR):
        exists_list = pickle.load(open(settings.TEACHER_DB_DIR, 'rb'))
        teacher_list.extend(exists_list)  # 对原有老师列表进行扩展
    pickle.dump(teacher_list, open(settings.TEACHER_DB_DIR, 'wb'))  # 将老师列表写入文件


def login(user, pwd):
    if os.path.exists(os.path.join(settings.BASE_ADMIN_DIR, user)):
        # 从文件中将管理员对象读取出来（里面封装了用户信息以及提供了登录方法）
        admin_obj = pickle.load(open(os.path.join(settings.BASE_ADMIN_DIR, user), 'rb'))
        if admin_obj.login(user, pwd):
            print('登录成功')
            while True:
                sel = input("1、创建老师；2、创建课程")
                if sel == '1':
                    create_teacher(admin_obj)
                elif sel == '2':
                    create_course(admin_obj)
                else:
                    break

        else:
            return 1
    else:
        return 0


def regiter(user, pwd):
    admin_obj = models.Admin()
    admin_obj.register(user, pwd)


def main():
    inp = input("1、管理员登录；2、管理员注册；\n >>>")
    user = input('请输入用户名:')
    pwd = input('请输入密码:')

    if inp == '1':
        ret = login(user, pwd)
        if ret == 1:
            print('密码错误')
        elif ret == 0:
            print('用户不存在')

    elif inp == '2':
        regiter(user, pwd)


if __name__ == "__main__":
    main()
