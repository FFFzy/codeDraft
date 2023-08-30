from sqlalchemy import Column, String, Integer, create_engine, ForeignKey
from sqlalchemy.orm import sessionmaker, declarative_base, relationship

Base = declarative_base()

class User(Base):
    __tablename__ = 'user' # 表名

    id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(20), nullable=False, index=True)
    city_id = Column(Integer, ForeignKey('city.id'))

class City(Base):
    __tablename__ = 'city' # 表名
    
    id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(20), nullable=False, index=True)
    users = relationship("User", backref="city")

engine = create_engine(
    "mysql+pymysql://root:root@localhost:3306/test",
    pool_size=10, #连接池大小
    pool_recycle=1600, # 连接回收时间
    pool_pre_ping=True, #预检测池中连接是否有效
    pool_use_lifo=True,
    echo_pool=True,
    max_overflow=5 #最大允许溢出的连接数量
)
Base.metadata.create_all(engine)
DBSession = sessionmaker(bind=engine)

if __name__  == '__main__':
    session = DBSession() # 创建session对象

    #增
    try:
        city = City(name="ShangHai", users=[User(name="A"),
                                            User(name="B")])
        session.add(city) # 添加到session
        session.commit() # 提交到数据库
    except Exception as e:
        session.rollback() #异常时回滚
        raise e
    
    #查
    res = session.query(User).all() # 查询全部符合条件的数据
    res = session.query(User).first() #查询符合条件的第一条数据
    res = session.query(User).filter(User.name=="A").first()
    if res:
        print(res.id, res.name, res.city.name)
    
    #删
    delete_user = session.query(User).filter(User.name == "C").first()
    if delete_user:
        session.delete(delete_user)
        session.commit()
    
    #改
    session.query(User).filter(User.id==1).update({'name': 'Jack'})
    session.commit()
    
    # 批量添加
    session.execute(
        City.__table__.insert(),
        [{"name": 'S' + str(i)} for i in range(10000)]
    )
    session.commit()

    session.close() # 关闭session