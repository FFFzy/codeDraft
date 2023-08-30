
import os
import sys

sys.path.append(os.path.dirname(os.path.dirname(__file__)))
import pickle
from lib import models
import settings


def course_info(student_obj):  # 打印已选课程信息
    for item in student_obj.course_list:
        print(item.course_name, item.teacher.name)


def select_course(student_obj):  # 选择课程
    course_list = pickle.load(open(settings.COURSE_DB_DIR, 'rb'))  # 从文件读取课程
    for num, item in enumerate(course_list, 1):
        print(num, item.course_name, item.cost, item.teacher.name)  # 打印课程列表
    while True:
        num = input("请选择课程(q退出):")
        if num == "q":
            break;
        selected_course_obj = course_list[int(num) - 1]  # 根据序号选择课程
        if selected_course_obj not in student_obj.course_list:
            student_obj.course_list.append(selected_course_obj)  # 添加进该学生的课程列表
    pickle.dump(student_obj, open(os.path.join(settings.BASE_STUDENTS_DIR, student_obj.username),
                                  'wb'))  # 将学生对象dump进文件，封装学生选课列表，上课字典信息


def login(user, pwd):
    if os.path.exists(os.path.join(settings.BASE_STUDENTS_DIR, user)):
        student_obj = pickle.load(open(os.path.join(settings.BASE_STUDENTS_DIR, user), 'rb'))
        if student_obj.login(user, pwd):  # 如果登陆成功
            while True:
                inp = input('1、选课；2、上课；3、查看课程信息')
                if inp == '1':
                    select_course(student_obj)
                elif inp == '3':
                    course_info(student_obj)
                else:
                    break
        else:
            print('密码错误')
    else:
        print('用户不存在')


def register(user, pwd):
    obj = models.Student()
    obj.register(user, pwd)


def main():
    inp = input('1、登录；2、注册 \n >>>')
    user = input("用户名：")
    pwd = input("密码：")
    if inp == "1":
        login(user, pwd)
    elif inp == "2":
        register(user, pwd)


if __name__ == "__main__":
    main()
