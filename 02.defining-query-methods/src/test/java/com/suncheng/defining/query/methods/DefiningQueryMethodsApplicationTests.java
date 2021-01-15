package com.suncheng.defining.query.methods;

import com.alibaba.fastjson.JSONObject;
import com.suncheng.defining.query.methods.entity.User;
import com.suncheng.defining.query.methods.repository.UserRepository;
import com.suncheng.defining.query.methods.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
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
    }

    @Test //根据邮箱后缀查询
    public void findByEmailEndingWith(){
        List<User> userList = userRepository.findByEmailEndingWith("@dturlv.ba");
        userList.forEach(System.out::println);
    }

}
