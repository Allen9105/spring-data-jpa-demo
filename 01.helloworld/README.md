# Spring Data JPA

可以理解为JPA规范的再次封装抽象，底层还是使用了 `Hibernate` 的JPA技术实现，引用 `JPQL`（Java Persistence Query Language）查询语言，属于Spring整个生态体系的一部分。

### JPA介绍

JPA是Java Persistence API的简称，中文名为Java持久层API，是JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。

![Repository关系结构图](document\image\Repository关系结构图.png)

<center>Spring Data JPA的主要类及结构图</center>

- <font style="color: blue">**蓝色实线箭头**</font> `继承`

- <font style="color: green">**绿色虚线箭头**</font> `接口`

------

![基础查询方法](document\image\基础查询方法.png)

<center>Spring Data JPA 基础查询方法</center>

---

### Repository详解

Spring Data JPA同时支持 `NoSQL` 和 `关系型数据库` ，从 `JpaRepository` 开始是对关系型数据库进行抽象封装。`JpaRepository` 继承了 `PagingAndSortingRepository` 类，也就拥有了其所有方法，并且实现类也是`SimpleJpaRepository` ，拥有了 `QueryByExampleExecutor` 的相关方法。

`JpaRepository`支持 `Query By Example`，批量删除，提高删除效率，手动刷新数据库的更改方法，并将默认实现的查询结果变成了List。

> `NoSQL`：
>
> - CrudRepository
> - PagingAndSortingRepository
>
> `关系型数据库`：
>
> - JpaRepository

#### CrudRepository 

<center>CrudRepository</center>

|       返回        | 方法                              | 介绍             | 备注                                      |
| :---------------: | :-------------------------------- | :--------------- | :---------------------------------------- |
| `<S extends T> S` | save(S var1)                      | 添加 与 修改     | 主键  ?（查  ?  修改  :  新增）:  新增    |
|  `<S extends T>`  | saveAll(Iterable<S> var1)         | 同时新增多条数据 | 多条insert，底层调save()，并非真实batch() |
|   `Optional<T>`   | findById(ID var1)                 | 通过主键查询     |                                           |
|     `boolean`     | existsById(ID var1)               | 判断主键是否存在 |                                           |
|   `Iterable<T>`   | findAll()                         | 查询所有数据     | 无数据 ? 空list : list，默认升序          |
|   `Iterable<T>`   | findAllById(Iterable<ID> var1)    | 通过多条主键查询 | 一条select，底层in()查询                  |
|      `long`       | count()                           | 查询表中记录条数 | 表中无数据时，返回0                       |
|                   | deleteById(ID var1)               | 根据主键删除数据 | 不存在抛EmptyResultDataAccessException    |
|                   | delete(T var1)                    | 根据实体主键删除 | 实体主键不存在时，不会抛异常              |
|                   | deleteAll(Iterable<? extends T> ) | 多个实体主键删除 | 值不存在时，只执行select                  |
|                   | deleteAll()                       | 删除表中所有数据 | 先查出所有数据，一条条删                  |

#### PagingAndSortingRepository

<center>PagingAndSortingRepository</center>

|     返回      | 方法                   | 介绍             | 备注 |
| :-----------: | ---------------------- | ---------------- | ---- |
| `Iterable<T>` | findAll(Sort var1)     | 排序查询全部数据 |      |
|   `Page<T>`   | findAll(Pageable var1) | 分页查询全部数据 |      |

#### JpaRepository

<center>JpaRepository</center>

|          返回           | 方法                                | 介绍                            | 备注                            |
| :---------------------: | ----------------------------------- | ------------------------------- | ------------------------------- |
|        `List<T>`        | findAll()                           | 查询所有数据                    |                                 |
|        `List<T>`        | findAll(Sort var1)                  | 查询所有数据，并排序            |                                 |
|        `List<T>`        | findAllById(Iterable<ID> var1)      | 通过多条主键查询                |                                 |
|                         | flush()                             | 刷新数据                        |                                 |
|    `<S extends T> S`    | saveAndFlush(S var1)                | 添加 并 刷新数据                |                                 |
|                         | deleteInBatch(Iterable<T> var1)     | 批量删除                        | 一条delete，底层or id = ? or    |
|           `T`           | getOne(ID var1)                     | 根据主键查询一条数据            | 不存在抛EntityNotFoundException |
| `<S extends T> List<S>` | findAll(Example<S> var1)            | 按照Example中实体属性查         | 实体所有属性为null，则查询所有  |
| `<S extends T> List<S>` | findAll(Example<S> var1, Sort var2) | 按照Example中实体属性查，并分页 |                                 |



### 附录

- 如果想在 `IDEA` 中查看 `UML` 图，`右键 -> Diagrams -> Show Diagram` ，快捷键 `Ctrl + Alt + Shift + U` 
- 如果想看 `Repository` 所有子类，`工具栏 Navigate ->Type Hierarchy` ，快捷键 `Ctrl + H` 