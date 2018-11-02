# Redis

## Command

### Key


- DEL（删除）

  `del key [key...]`
  
  功能：删除给定的一个或者多个key
 
  复杂度：`O(n)`，其中`n`为key的数量。
  
      删除单个字符串类型的 key ，时间复杂度为O(1)。删除单个列表、集合、有序集合或哈希表类型的 key ，时间复杂度为O(M)， M 为以上数据结构内的元素数量。
 
  返回值：被删除的key的数量

- EXISTS （存在）

  `exists key`
  
  功能：检查$key是否存在

  复杂度：O(1)

  返回值：存在返回1，不存在返回0
  
- EXPIRE （过期时间）

  `expire key seconds`
  
   功能：为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
  
   复杂度：O(1)
  
   返回值：成功返回1，不存在或者设置失败返回0
  
  
- EXPIREAT （过期时间）

  `expireat key timestamp`
  
   功能：为给定key指定生存时间节点 ，当 key 过期时(生存时间为 0 )，它会被自动删除。
  
   复杂度：O(1)
  
   返回值：成功返回1，不存在或者设置失败返回0
   
   
- SORT 

  `SORT key [BY pattern] [LIMIT offset count] [GET pattern [GET pattern ...]] [ASC | DESC] [ALPHA] [STORE destination]`
  
  功能：返回或保存给定列表、集合、有序集合 key 中经过排序的元素。
  
  复杂度：O(N+M*log(M))， N 为要排序的列表或集合内的元素数量， M 为要返回的元素数量。
      如果只是使用 SORT 命令的 GET 选项获取数据而没有进行排序，时间复杂度 O(N)。
  
  返回值：没有使用 STORE 参数，返回列表形式的排序结果，使用 STORE 参数，返回排序结果的元素数量。


- TTL

  `ttl key`
  
  功能：
  > 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
  
  复杂度：O(1)
  
  返回值：
  > -2，key不存在；-1，没有剩余时间；其他返回剩余时间。

### String

- APPEND

  `append key value`
  
  功能：
  
  > 如果 `key` 已经存在并且是一个字符串， `APPEND`命令将`value`追加到`key`原来的值的末尾。
    如果 `key` 不存在， `APPEND` 就简单地将给定`key`设为`value`，就像执行`SET key value`一样。
  
  复杂度：O(1)
  
  返回值：追加后
  
  
- DECR

  ``

  功能：
  > 将 key 中储存的数字值减一。
    如果 `key`不存在，那么`key`的值会先被初始化为`0`，然后再执行 `DECR`操作。
    如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
  
  复杂度：O(1)
  
  返回值：
  > 执行 DECR 命令之后 key 的值。
  
  
- DECRBY

  `DECRBY key decrement`

  功能：
  > 将 key 所储存的值减去减量 decrement 。
  
  复杂度：O(1)
  
  返回值：
  > 减去 decrement 之后， key 的值。
  
  
- GET

  ``

  功能：
  > 获取`key`所关联的字符串值。
  
  复杂度：O(1)
  
  返回值：
  > 当 key 不存在时，返回 nil ，否则，返回 key 的值。
    如果 key 不是字符串类型，那么返回一个错误。
  
  
- GETBIT

  `GETBIT key offset`

  功能：
  > 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
  
  复杂度：O(1)
  
  返回值：
  > 字符串值指定偏移量上的位(bit), 当 offset 比字符串值的长度大，或者 key 不存在时，返回 0 。
  
  
- GETRANGE

  `GETRANGE key start end`

  功能：
  > 返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
    负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
  
  复杂度：O(n)
  
  返回值：截取得出的子字符串。
  
  
- GETSET

  `GETSET key value`

  功能：
  > 获取给定 key 的值 ，并重设给定的key的值为value
  
  复杂度：O(1)
  
  返回值：
  > key的旧值,key不存在时返回`nil`
  
  
- INCR

  `INCR key`

  功能：
  > 将 key 中储存的数字值增一。
  
  复杂度：O(1)
  
  返回值：
  > 计算完成后的值
  
  
- INCRBY

  `INCRBY key increment`

  功能：
  > 将 key 所储存的值加上增量 increment 。
  
  复杂度：O(1)
  
  返回值：
  > 计算完成后的值
  
  
- INCRBYFLOAT

  `INCRBYFLOAT key increment`

  功能：
  > 为 key 中所储存的值加上浮点数增量 increment 
  
  复杂度：O(1)
  
  返回值：
  > 计算后的值
  
  
- MGET

  `MGET key [key ...]`

  功能：
  > 一次获取多个给定的key所存储的值
  
  复杂度：O(n)
  
  返回值：
  > 所有key对应的value的列表
  
  
- MSET

  `MSET key value [key value ...]`

  功能：
  > 设置一次给定的多个健值对，存在则覆盖。
  
  复杂度：O(n)
  
  返回值：
  > ok
  
  
- MSETNX

  `MSETNX key value [key value ...]`

  功能：
  > 一次设置多个给定的健值对且任意key均不存在
  
  复杂度：O(n)
  
  返回值：
  > 1全部设置成功，0至少一个key已经存在
  
  
- PSETEX

  `PSETEX key milliseconds value`

  功能：
  > 设置给定的key所存储的值为value，生存时长为milliseconds(ms)
  
  复杂度：O(1)
  
  返回值：ok
  
  
- SET

  `SET key value [EX seconds] [PX milliseconds] [NX|XX]`

  功能：
  > 设置给定的key所存储的值为value，生存时长为seconds(s) milliseconds(ms)
  
  复杂度：O(1)
  
  返回值：
  > ok
  
  
- SETBIT

  `SETBIT key offset 0|1`

  功能：
  > 设置给定key所存储的值的第offset位的bit值为0|1
  
  复杂度：O(1)
  
  返回值：
  > 原offset位的bit值
  
  
- SETEX

  `SETEX key seconds value`

  功能：设置给定的key的值为value，并设置生存时长为seconds(s,单位)
  
  复杂度：O(1)
  
  返回值：
  > 设置成功返回ok
  
  
- SETNX

  `SETNX key value`

  功能：
  > 不存在时设置key存储的值为value。（SET if Not Exists）
  
  复杂度：O(1)
  
  返回值：
  > 1成功，0失败
  
  
- STRLEN

  `STRLEN key`

  功能：
  > 获取给定key所存储的字符串的长度
  
  复杂度：O(1)
  
  返回值：
  > 字符串长度，key不存在时返回0
  

### Hash

### List

### Set

### Sorted Set

### Publish/Subscribe

### Transaction 


