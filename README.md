## 关于性能

性能高于基于动态标签的框架,为了有个直观的表述,选取了另一个优秀的国内最火的MyBatis增强框架[MyBatis-Plus](https://baomidou.com/) 作为参照.

测试基于JMH,直接测试Mapper方法,不知这样是否有问题?如果该测试方法不对,请指正.测试数据为HSQL,测试时仅建表.表中没有数据.两边尽可能使用相类似的API ,参数保持一致.

##### 硬件版本

CPU: 4800U

RAM: 16G

##### 软件版本

JMH : 1.34

VM : JDK 17, Java HotSpot(TM) 64-Bit Server VM, 17+35-LTS-2724

其他: 基于当前时间2022-01-29号为止,所有软件版本基于Maven中央仓库最新版本.

测试代码库:[speed-test](https://github.com/NoSugarIce/speed-test)

测试结果每次都会有少许差异(不大),会运行两次,取最高成绩.可以访问测试代码块直接运行测试代码库的测试类.

insert:9个字段

selectById:一个查询字段

selectListByCriteriaP5:5个查询字段

selectListByCriteriaP9:9个查询字段

selectPage:9个查询字段

![](https://gitee.com/NoSugarIce/document-gallery/raw/master/mybatis-nosugar/jmh-20220129.svg)

源测试结果

````
PlusMapperSpeed.insert                  thrpt    5  25552.475 ±  993.646  ops/s
PlusMapperSpeed.selectById              thrpt    5  63415.578 ± 1188.073  ops/s
PlusMapperSpeed.selectListByCriteriaP5  thrpt    5   6785.299 ±  105.349  ops/s
PlusMapperSpeed.selectListByCriteriaP9  thrpt    5   2285.114 ±  113.529  ops/s
PlusMapperSpeed.selectPage              thrpt    5   1297.362 ±  508.053  ops/s

Benchmark                                Mode  Cnt      Score      Error  Units
PlusMapperSpeed.insert                  thrpt    5  27252.614 ± 1034.138  ops/s
PlusMapperSpeed.selectById              thrpt    5  63406.468 ± 1343.942  ops/s
PlusMapperSpeed.selectListByCriteriaP5  thrpt    5   6636.624 ±  726.123  ops/s
PlusMapperSpeed.selectListByCriteriaP9  thrpt    5   2177.976 ±  402.546  ops/s
PlusMapperSpeed.selectPage              thrpt    5   1470.102 ±   87.056  ops/s


Benchmark                                   Mode  Cnt      Score      Error  Units
NoSugarMapperSpeed.insert                  thrpt    5  47988.211 ± 2970.781  ops/s
NoSugarMapperSpeed.selectById              thrpt    5  63237.050 ± 2012.060  ops/s
NoSugarMapperSpeed.selectListByCriteriaP5  thrpt    5  30798.026 ± 1670.957  ops/s
NoSugarMapperSpeed.selectListByCriteriaP9  thrpt    5  21521.540 ±  712.482  ops/s
NoSugarMapperSpeed.selectPage              thrpt    5  19602.208 ±  464.896  ops/s

Benchmark                                   Mode  Cnt      Score      Error  Units
NoSugarMapperSpeed.insert                  thrpt    5  47884.604 ± 3913.094  ops/s
NoSugarMapperSpeed.selectById              thrpt    5  64194.566 ±  812.006  ops/s
NoSugarMapperSpeed.selectListByCriteriaP5  thrpt    5  31574.912 ±  263.361  ops/s
NoSugarMapperSpeed.selectListByCriteriaP9  thrpt    5  21053.232 ±  263.171  ops/s
NoSugarMapperSpeed.selectPage              thrpt    5  19431.988 ±  501.469  ops/s
````