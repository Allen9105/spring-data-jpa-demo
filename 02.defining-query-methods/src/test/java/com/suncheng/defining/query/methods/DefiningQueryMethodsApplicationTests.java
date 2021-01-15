package com.suncheng.defining.query.methods;

import com.alibaba.fastjson.JSONObject;
import com.suncheng.defining.query.methods.entity.User;
import com.suncheng.defining.query.methods.repository.UserRepository;
import com.suncheng.defining.query.methods.service.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@SpringBootTest
class DefiningQueryMethodsApplicationTests {

    @Resource
    private UserService userService;
    @Resource
    private UserRepository userRepository;

    @Test //批量插入数据
    public void addBatch(){
        String str = "[{\"name\":\"孟娟\",\"email\":\"s.dgmkdgv@zhokx.br\",\"createTime\":\"1971-16-18 08:16:59\"},{\"name\":\"石静\",\"email\":\"p.spot@qhtowhqgss.jm\",\"createTime\":\"2018-00-16 00:00:46\"},{\"name\":\"罗秀英\",\"email\":\"m.yzczdqdiu@riq.mil\",\"createTime\":\"2015-44-18 03:44:35\"},{\"name\":\"姜军\",\"email\":\"i.nefeeuxpu@dturlv.ba\",\"createTime\":\"1975-25-25 05:25:49\"},{\"name\":\"郝静\",\"email\":\"t.pzpueyf@ongwbl.cf\",\"createTime\":\"1971-17-24 23:17:46\"},{\"name\":\"宋娟\",\"email\":\"m.pfa@kkcbeb.pl\",\"createTime\":\"1979-44-08 15:44:30\"},{\"name\":\"朱明\",\"email\":\"s.pnihovzsd@smybgc.gw\",\"createTime\":\"2012-18-26 21:18:46\"},{\"name\":\"梁磊\",\"email\":\"c.hnanxtymeg@yufqwryse.va\",\"createTime\":\"1978-03-25 22:03:16\"},{\"name\":\"贺强\",\"email\":\"h.hnaxvbimmp@eoljvcie.md\",\"createTime\":\"2017-16-02 22:16:58\"},{\"name\":\"曾超\",\"email\":\"e.vglnhx@gyxowy.tg\",\"createTime\":\"2014-07-09 15:07:15\"},{\"name\":\"任洋\",\"email\":\"m.trofldroy@pcofoygofi.co\",\"createTime\":\"2000-40-31 06:40:21\"},{\"name\":\"邹芳\",\"email\":\"i.meodeest@udfimskc.org\",\"createTime\":\"1983-21-27 19:21:23\"},{\"name\":\"李磊\",\"email\":\"x.odsezvqas@cpxos.gh\",\"createTime\":\"2017-55-21 14:55:30\"},{\"name\":\"高艳\",\"email\":\"g.hnxlilh@uojy.be\",\"createTime\":\"1997-59-20 19:59:03\"},{\"name\":\"郭娜\",\"email\":\"r.hteyduub@ltdqj.tk\",\"createTime\":\"1979-15-29 17:15:25\"},{\"name\":\"方洋\",\"email\":\"u.vedmruletx@ztckysgi.dm\",\"createTime\":\"1975-36-21 07:36:09\"},{\"name\":\"郭杰\",\"email\":\"q.twxo@vqyfl.vn\",\"createTime\":\"1976-52-14 05:52:48\"},{\"name\":\"康敏\",\"email\":\"q.ntwcwtgxr@hstuh.au\",\"createTime\":\"1988-30-28 23:30:51\"},{\"name\":\"孟明\",\"email\":\"m.doxtlx@yxseppq.cy\",\"createTime\":\"2004-17-01 18:17:19\"},{\"name\":\"罗艳\",\"email\":\"d.qjrq@hshbbhplow.in\",\"createTime\":\"1991-20-13 20:20:23\"},{\"name\":\"马洋\",\"email\":\"f.usewonfs@eslcjlw.mo\",\"createTime\":\"1989-53-07 09:53:00\"},{\"name\":\"唐芳\",\"email\":\"y.kvcw@bjwk.hm\",\"createTime\":\"1977-30-25 16:30:37\"},{\"name\":\"汪静\",\"email\":\"i.sotebqj@javmeweiu.lb\",\"createTime\":\"1975-27-12 05:27:27\"},{\"name\":\"袁洋\",\"email\":\"b.sipbqxspi@mlqbgy.cn\",\"createTime\":\"1974-59-03 12:59:33\"},{\"name\":\"马芳\",\"email\":\"p.axuu@esbx.tv\",\"createTime\":\"1991-52-20 10:52:22\"},{\"name\":\"顾静\",\"email\":\"t.bdjdgtogie@vlbddldjf.gn\",\"createTime\":\"2010-19-18 16:19:33\"},{\"name\":\"王杰\",\"email\":\"i.rccygcmuhg@drkgvnuwf.qa\",\"createTime\":\"1972-59-16 01:59:39\"},{\"name\":\"江娟\",\"email\":\"m.mymsc@vrrexdv.fo\",\"createTime\":\"1972-14-26 05:14:12\"},{\"name\":\"康敏\",\"email\":\"e.jjitniwz@ppccdkok.info\",\"createTime\":\"1988-42-15 10:42:37\"},{\"name\":\"赵桂英\",\"email\":\"i.mrynzvpkyh@vgdjdy.coop\",\"createTime\":\"1984-28-28 02:28:14\"},{\"name\":\"宋艳\",\"email\":\"t.ltjwg@jjre.st\",\"createTime\":\"1982-32-27 01:32:19\"},{\"name\":\"赵霞\",\"email\":\"w.fbxfkrd@gurwksi.to\",\"createTime\":\"1999-36-01 15:36:14\"}]";
        List<User> userList = JSONObject.parseArray(str, User.class);
        userService.saveBatch(userList);
        /*
          打印：
            Hibernate: insert into user (create_time, name) values (?, ?)
            Hibernate: insert into user (create_time, name) values (?, ?)
            ....
        */
    }

    @Test //[通用] 按照时间段查询并倒序
    public void findByCreateTimeBetweenOrderByCreateTimeDesc() throws Exception{
        Date startDate = DateUtils.parseDate("2000-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date endDate = DateUtils.parseDate("2020-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        List<User> userList = userRepository.findByCreateTimeBetweenOrderByCreateTimeDesc(startDate, endDate);
        userList.forEach(System.out::println);
        /*
           打印：
             Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.email as email3_0_, user0_.name as name4_0_ from user user0_ where user0_.create_time between ? and ? order by user0_.create_time desc
             User(id=3, name=罗秀英, email=m.yzczdqdiu@riq.mil, createTime=2018-08-18 03:44:35.0)
             User(id=9, name=贺强, email=h.hnaxvbimmp@eoljvcie.md, createTime=2018-04-02 22:16:58.0)
             User(id=2, name=石静, email=p.spot@qhtowhqgss.jm, createTime=2017-12-16 00:00:46.0)
             ....
         */
    }

    @Test //[通用] 按照时间段查询总条数
    public void countByCreateTimeBetweenOrderByCreateTime() throws Exception{
        Date startDate = DateUtils.parseDate("2000-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date endDate = DateUtils.parseDate("2020-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        long count = userRepository.countByCreateTimeBetweenOrderByCreateTime(startDate, endDate);
        System.out.println(count);
        /*
           打印：
             Hibernate: select count(user0_.id) as col_0_0_ from user user0_ where user0_.create_time between ? and ?
             10
         */
    }

    @Test //根据邮箱后缀查询
    public void findByEmailEndingWith(){
        //为了方便演示，将郭娜的email后缀改为@dturlv.ba
        List<User> userList = userRepository.findByEmailEndingWith("@dturlv.ba");
        userList.forEach(System.out::println);
        /*
           打印：
             Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.email as email3_0_, user0_.name as name4_0_ from user user0_ where user0_.email like ? escape ?
             User(id=4, name=姜军, email=i.nefeeuxpu@dturlv.ba, createTime=1977-01-25 05:25:49.0)
             User(id=15, name=郭娜, email=r.hteyduub@dturlv.ba, createTime=1980-03-29 17:15:25.0)
         */
    }

    @Test //根据邮箱地址查询（忽略大小写）
    public void findByEmailIgnoreCase(){
        User user = userRepository.findByEmailIgnoreCase("M.MYMSC@VRREXDV.FO");
        System.out.println(user);
        /*
           打印：
             Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.email as email3_0_, user0_.name as name4_0_ from user user0_ where upper(user0_.email)=upper(?)
             User(id=28, name=江娟, email=M.mYmsc@vrrexdv.fo, createTime=1973-02-26 05:14:12.0)
         */
    }

    @Test //根据姓名删除
    @Transactional //如果不加事物会报错，如果只加事物不加回滚，会只SELECT 然后回滚，并不会执行DELETE操作
    @Rollback(value = false) //不回滚
    public void deleteByName(){
        long row = userRepository.deleteByName("赵霞");
        System.out.println(row);
        /*
           打印：
             Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.email as email3_0_, user0_.name as name4_0_ from user user0_ where user0_.name=?
             1
             Hibernate: delete from user where id=?
         */
    }

}
