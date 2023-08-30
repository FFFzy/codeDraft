import os
BASE_DIR = os.path.dirname(os.path.dirname(__file__))  #配置文件的上级目录
BASE_ADMIN_DIR = os.path.join(BASE_DIR, "db", "admin")  #管理员目录
BASE_STUDENTS_DIR = os.path.join(BASE_DIR, "db", "students")  #学生目录
TEACHER_DB_DIR = os.path.join(BASE_DIR, "db", "teacher_list") #老师列表目录
COURSE_DB_DIR = os.path.join(BASE_DIR, "db", "course_list")  #课程列表目录
