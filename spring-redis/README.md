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
  
  功能：以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
  
  复杂度：O(1)
  
  返回值：-2，key不存在；-1，没有剩余时间；其他返回剩余时间。

### String

- APPEND
- BITCOUNT

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- BITOP

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- DECR

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- DECRBY

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- GET

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- GETBIT

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- GETRANGE

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- GETSET

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- INCR

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- INCRBY

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- INCRBYFLOAT

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- MGET

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- MSET

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- MSETNX

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- PSETEX

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- SET

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- SETBIT

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- SETEX

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- SETNX

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- SETRANGE

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  
  
- STRLEN

  ``
  功能：
  
  复杂度：O()
  
  返回值：
  

### Hash

### List

### Set

### Sorted Set

### Publish/Subscribe

### Transaction 


