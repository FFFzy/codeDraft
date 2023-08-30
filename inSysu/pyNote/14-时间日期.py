## 时间
import time

t = time.time()         
print(t)  # 1594974068.2558458

lt = time.localtime(t)  
print(lt) # time.struct_time(tm_year=2020, tm_mon=7, tm_mday=17, tm_hour=16, tm_min=22, tm_sec=2, tm_wday=4, tm_yday=199, tm_isdst=0)

at = time.asctime(lt)   
print(at) # Fri Jul 17 16:23:03 2020

# 时间戳转字符串
print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))   
# 2020-07-17 16:26:02
print(time.strftime("%a %b %d %H:%M:%S %Y", time.localtime()))
# Fri Jul 17 16:26:02 2020

# 将格式字符串转换为时间戳
a = "Fri Jul 17 16:26:02 2020"
print(time.mktime(time.strptime(a,"%a %b %d %H:%M:%S %Y")))
# 1594974362.0


## 日期
from datetime import datetime, timedelta, timezone, date

dt = datetime(2020, 7, 17, 16, 35)
dt = datetime.now()

t = dt.timestamp()
dt = datetime.fromtimestamp(1594974900.0)

# datetime和string
print(datetime.strptime('2020-7-17 16:35:59', '%Y-%m-%d %H:%M:%S'))  
# str转换为datetime  2020-07-17 16:35:59
print(datetime.now().strftime('%a, %b %d %H:%M'))                    
# datetime转换为str  Fri, Jul 17 16:38 

# datetime加减
now = datetime.now()     
print(now)               # 2020-07-17 16:40:40.539739
now += timedelta(days=2, hours=12)
now -= timedelta(days=1)
print(now)               # 2020-07-19 04:40:14.693033

# date和timestamp
d = datetime.now().date()          # 2023-07-09
d = date.today()                   # 2023-07-09

t = time.mktime(d.timetuple())     # date转timestamp
d = date.fromtimestamp(t)          # timestamp转date
