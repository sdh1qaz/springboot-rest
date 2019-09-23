title redisºØ»∫∑÷≈‰≤€
cd /d  "E:\redis-cluster\redis_6380"

for /l %%i in (0,1,5461) do (
redis-cli -h 127.0.0.1 -p 6380 cluster addslots %%i
)

for /l %%i in (5462,1,10922) do (
redis-cli -h 127.0.0.1 -p 6381 cluster addslots %%i
)

for /l %%i in (10923,1,16383) do (
redis-cli -h 127.0.0.1 -p 6382 cluster addslots %%i
)

pause