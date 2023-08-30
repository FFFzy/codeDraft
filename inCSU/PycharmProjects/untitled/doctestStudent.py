def login(user, pwd):
    """
    >>> login("","")
    用户账号或者密码输入为空
    >>> login("a","1")
    账号或者密码错误
    >>> login("a","123")
    登录成功
    """
    if ("".__eq__(user) | "".__eq__(pwd)):
        print('用户账号或者密码输入为空')
    else:
        if ("a".__eq__(user) & "123".__eq__(pwd) ):  # 如果登陆成功
            print("登录成功")
        else:
            print('账号或者密码错误')


if __name__ == '__main__':
    import doctest
    doctest.testmod(verbose=True)
