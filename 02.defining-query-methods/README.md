# Spring Data JPA

### 批量插入数据

为了方便后续学习，我们先插入一组数据。

#### 普通批量插入

```java
@Test //批量插入数据
public void addBatch(){
    //为了可读性，我们就拿2条数据作为批量
    String str = "[{\"name\":\"孟娟\",\"email\":\"s.dgmkdgv@zhokx.br\",\"createTime\":\"1971-16-18 08:16:59\"},{\"name\":\"石静\",\"email\":\"p.spot@qhtowhqgss.jm\",\"createTime\":\"2018-00-16 00:00:46\"}]";
    List<User> userList = JSONObject.parseArray(str, User.class);
    userList.forEach(item -> userRepository.saveAll(userList));
    /*
      打印：
      	Hibernate: insert into user (create_time, name) values (?, ?)
        Hibernate: insert into user (create_time, name) values (?, ?)
    */
}
```

从以上代码中，我们使用 `JPA` 默认的 `saveAll()` 方法。

#### 优化批量插入

通过查看 `saveAll()` 源码发现，底层调用 `save()` 方法，如果对速度有要求，我们可以小小的提升一下。`只能提升一点点` 

```java
//省略UserService，直接UserServiceImpl
@Service
public class UserServiceImpl implements UserService {
    @PersistenceContext //通过该注解获取容器托管的EntityManager对象
    private EntityManager em;
    
    @Override
    @Transactional
    public void saveBatch(List<User> userList) {
        userList.forEach(user ->
            em.persist(user)
        );
        //不需要关闭操作 em.close()
    }
}

//测试
@Resource
private UserService userService;
@Test
public void saveBatch(){
    String str = "[{\"name\":\"孟娟\",\"email\":\"s.dgmkdgv@zhokx.br\",\"createTime\":\"1971-16-18 08:16:59\"},{\"name\":\"石静\",\"email\":\"p.spot@qhtowhqgss.jm\",\"createTime\":\"2018-00-16 00:00:46\"}]";
    List<User> userList = JSONObject.parseArray(str, User.class);
    userService.saveBatch(userList);
}
```



### 自定义查询策略

#### 查询关键字列表

|         关键字         | 示例                                        | JPQL片段                                      |
| :--------------------: | ------------------------------------------- | --------------------------------------------- |
|         `And`          | findByLastnameAndFirstname                  | WHERE x.lastname = ?1 AND x.firstname = ?2    |
|          `OR`          | findByLastnameOrFirstname                   | WHERE x.lastname = ?1 OR x.firstname = ?2     |
|     `Is`, `Equals`     | findByFirstname, findByxxIs, findByxxEquals | WHERE x.fistname = ?1                         |
|       `Between`        | findByStartDateBetween                      | WHERE x.startDate BETWEEn ?1 AND ?2           |
|       `LessThan`       | findByAgeLessThan                           | WHERE x.age < ?1                              |
|    `LessThanEqual`     | findByAgeLessThanEqual                      | WHERE x.age <= ?1                             |
|     `GreaterThan`      | findByAgeGreaterThan                        | WHERE x.age > ?1                              |
|   `GreaterThanEqual`   | findByAgeGreaterThanEqual                   | WHERE x.age >= ?1                             |
|        `After`         | findByStartDateAfter                        | WHERE x.startDate > ?1                        |
|        `Before`        | findByStartDateBefore                       | WHERE x.startDate < ?1                        |
|        `IsNull`        | findByAgeIsNull                             | WHERE x.age IS NULL                           |
| `IsNotNUll`, `NotNull` | findByAge(Is)NotNull                        | WHERE x.age IS NOT NULL                       |
|         `Like`         | findByFirstnameLike                         | WHERE x.firstname LIKE ?1                     |
|       `NotLike`        | findByFirstnameNotLike                      | WHERE x.firstname NOT LIKE ?1                 |
|     `StartingWith`     | findByFirstnameStartingWith                 | WHERE x.firstname LIKE ?1 `参数绑定后附%`     |
|      `EndingWith`      | findByFirstnameEndingWith                   | WHERE x.firstname LIKE ?1 `参数与前面的%绑定` |
|      `Containing`      | findByFirstnameContaining                   | WHERE x.firstname LIKE ?1 `绑定在%中的参数`   |
|       `OrderBy`        | findByAgeOrderByLastnameDesc                | WHERE x.age = ?1 ORDER BY x.lastname DESC     |
|         `Not`          | findByLastnameNot                           | WHERE x.lastname <> ?1                        |
|          `In`          | findByAgeIn(Collection<Age> ages)           | WHERE x.age IN ?1                             |
|        `NotIn`         | findByAgeNotIn(Collection<Age> age)         | WHERE x.age NOT IN ?1                         |
|         `True`         | findByActiveTrue()                          | WHERE x.active = true                         |
|        `Flase`         | findByActiveFalse()                         | WHERE x.active = false                        |
|      `IgnoreCase`      | findByFirstnameIgnoreCase                   | WHERE UPPER(x.firstname) = UPPER(?1)          |

除了 `find` 的前缀之外，我们查看 `PartTree.class` 的源码，还有如下几种前缀

```java
private static final String QUERY_PATTERN = "find|read|get|query|search|stream";
private static final String COUNT_PATTERN = "count";
private static final String EXISTS_PATTERN = "exists";
private static final String DELETE_PATTERN = "delete|remove";

//例如：
	long countByLastName(String lastname); //根据一个字段查询总条数
	long deleteByLastname(String lastname); //根据一个字段进行删除操作
	List<User> removeByLastname(String lastname); //根据一个字段进行删除操作
```



#### 方法返回值列表

|          返回类型          | 描述                                                         |
| :------------------------: | ------------------------------------------------------------ |
|           `void`           | 表示无返回值                                                 |
|    `基元` (Primitives)     | Java原语 (Java primitives)                                   |
| `包装类型` (Wrapper types) | Java包装器类型 (Java wrapper types)                          |
|            `T`             | 最多返回一个结果，未找到结果 ? null : `IncorrectResultSizeDataAccessException` |
|       `Iterator<T>`        | 返回一个 Iterator 集合                                       |
|      `Collection<T>`       | 返回一个集合                                                 |
|         `List<T>`          | 返回一个 List 集合                                           |
|       `Optional<T>`        | 最多返回一个结果，未找到结果 `IncorrectResultSizeDataAccessException` |
|       `Optional<T>`        | `Scala` 或 `JavaSlang Option` 类型。与上述 `Java 8` 的 `Optional` 的行为相同 |
|        `Stream<T>`         | 返回一个 `Java 8 Stream` 流                                  |
|        `Future<T>`         | 期待使用 `@Async` 注释的方法，并且需要启用 `Spring` 的异步方法执行功能 |
|   `CompletableFuture<T>`   | `Java 8 CompletableFuture` 期望使用  `@Async` 注释并且需要启用Spring的异步方法执行功能的方法 |
|     `ListenableFuture`     | `org.springframework.util.concurrent.ListenableFuture`，需要加 `@Async` 注解，并且 `Spring` 开启异步 |
|          `Slice`           | 一组固定大小的数据，可以判断是否有更多数据，需要 `Pageable` 参数 |
|         `Page<T>`          | 分页，包括一页的数据，元素总数，总共页数等，需要 `Pageable` 参数 |
|       `GeoResult<T>`       | 带有附加信息的结果条目，例如到参考位置的距离                 |
|      `GeoResults<T>`       | 包含附加信息的 `GeoResult<T>` 列表，例如到参考位置的平均距离 |
|        `GeoPage<T>`        | 具有 `GeoResult<T>` 的 `Page` ，例如到参考位置的平均距离     |



#### 自定义Repository

##### 1. 继承Repository接口

```java
/**
 * 定义通用Repository（选择性暴露Repository中方法）
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean //让Spring Data 不去实例化 repository
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
    
    List<T> findByCreateTimeBetween(Date startDate, Date endDate);
	
    List<T> findByCreateTimeBetweenOrderByCreateTimeDesc(Date startDate, Date endDate);

}
```

##### 2. @RepositoryDefinition

```java
/**
 * 使用 @RepositoryDefinition 注解，定义 UserRepository（选择性暴露UserRepository中方法）
 */
@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface MyUserRepository {

    Optional<User> findByName(String name);

    List<User> findByIdNotIn(Long[] ids);

}
```



### 查询策略设置

通过 `@EnableJpaRepositories(queryLookupStrategy=QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)` 可以配置方法的查询策略，其中 `QueryLookupStrategy.Key` 的值一共有三个。

- `CREATE`：直接根据方法名进行创建。规则是根据方法名称的构造进行尝试，一般的方法是从方法名中删除给定的一组已知前缀，并解析该方法的其余部分。如果方法名不符合规则，启动的时候就会报异常。
- `USE_DECLARED_QUERY`：声明方式创建（注解方式）。启动的时候会尝试找到一个声明的查询，如果没有找到就将抛出一个异常。查询可以由某处注释或其他方法声明。
- `CREATE_IF_NOT_FOUND`：这个是 `默认` 的，以上两种方式的结合版。先用声明方式进行查找，如果没有找到与方法相匹配的查询，就用create的方法名创建规则创建一个查询。

说明：除非有特殊需求，一般直接用默认的，不用管。

`QueryLookupStrategy` 是策略的定义接口，
`JpaQueryLookupStrategy` 是具体策略的实现类。

```java
@SpringBootApplication
//设置查询策略
@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class DefiningQueryMethodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefiningQueryMethodsApplication.class, args);
    }

}
```



