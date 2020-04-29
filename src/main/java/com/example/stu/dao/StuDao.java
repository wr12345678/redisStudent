package com.example.stu.dao;

import com.example.stu.pojo.Student;
import com.example.stu.util.RedisUtile;
import com.example.stu.util.StringDateUtil;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class StuDao {

    private Jedis jedis = RedisUtile.getJedis();

    public String addStu(Student student){
        String stuId = jedis.get("user:nextid");
        System.out.println(stuId+"user:"+stuId+":id");
        jedis.incr("user:nextid");
        jedis.set("user:"+stuId+":id",stuId);
        jedis.set("user:"+stuId+":name",student.getName());
        jedis.set("user:"+stuId+":birthday",StringDateUtil.dateToString(student.getBirthday()));
        jedis.set("user:"+stuId+":description",student.getDescription());
        String result = jedis.set("user:"+stuId+":avgscore",student.getAvgscore().toString());
        return  result;
    }

    public String deleteStu(Integer stuid){
        long result = 0;
        result += jedis.del("user:"+stuid.toString()+":id");
        result += jedis.del("user:"+stuid.toString()+":name");
        result += jedis.del("user:"+stuid.toString()+":birthday");
        result += jedis.del("user:"+stuid.toString()+":description");
        result += jedis.del("user:"+stuid.toString()+":avgscore");
        return result>0?"ok":"error";
    }


    public List<Student> getAllStu() throws ParseException {
        List<Student> list = new ArrayList<>();
        Integer userMaxId = Integer.parseInt(jedis.get("user:nextid"));
        for(int i=1;i<userMaxId;i++){
            if(jedis.get("user:"+i+":id")==null || jedis.get("user:"+i+":id").equals("")){
                continue;
            }else {
                Student student = new Student();
                student.setId(jedis.get("user:"+i+":id"));
                student.setName(jedis.get("user:"+i+":name"));
                student.setBirthday(StringDateUtil.stringToDate(jedis.get("user:"+i+":birthday").trim(),"yy-MM-dd"));
                student.setDescription(jedis.get("user:"+i+":description"));
                student.setAvgscore(Integer.parseInt(jedis.get("user:"+i+":avgscore")));
                list.add(student);
            }
        }
        return list;
    }

    public Student getStuById(Integer id) throws ParseException {
        Student student = new Student();
        student.setId(jedis.get("user:"+id+":id"));
        student.setName(jedis.get("user:"+id+":name"));
        student.setBirthday(StringDateUtil.stringToDate(jedis.get("user:"+id+":birthday").trim(),"yy-MM-dd"));
        student.setDescription(jedis.get("user:"+id+":description"));
        student.setAvgscore(Integer.parseInt(jedis.get("user:"+id+":avgscore")));
        return student;
    }

    public String updateStu(Student student){
        jedis.set("user:"+student.getId()+":name",student.getName());
        jedis.set("user:"+student.getId()+":birthday",StringDateUtil.dateToString(student.getBirthday()));
        jedis.set("user:"+student.getId()+":description",student.getDescription());
        String result = jedis.set("user:"+student.getId()+":avgscore",student.getAvgscore().toString());
        return result;
    }

    public void close(){
        jedis.close();
    }
}
