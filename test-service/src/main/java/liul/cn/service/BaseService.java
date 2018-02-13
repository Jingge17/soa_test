package liul.cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import liul.cn.entity.BasePojo;

public abstract class BaseService <T extends BasePojo>{
@Autowired
private Mapper<T> mapper;
/**
 * ����id��ѯ����
 * 
 * @param id
 * @return
 */
public T queryById(Long id) {
	return this.mapper.selectByPrimaryKey(id);
}
/**
 * ��ѯ��������
 * 
 * @return
 */
public List<T> queryAll(){
	return this.mapper.select(null);
}

/**
 * ����������ѯһ�����ݣ��������������ѯ������Ϊ�������׳��쳣
 * 
 * @param record
 * @return
 */
public T queryOne(T record) {
	return this.mapper.selectOne(record);
}

/**
 * ����������ѯ��������
 * 
 * @param record
 * @return
 */
public List<T> queryListByWhere(T record){
	return this.mapper.select(record);
}
/**
 * ����������ҳ��ѯ����
 * 
 * @param record
 * @param page
 * @param rows
 * @return
 */
public PageInfo<T> queryPageListByWhere(T record,Integer page,Integer rows){
	PageHelper.startPage(page,rows);
	List<T> list=this.mapper.select(record);
	return new PageInfo<T>(list);
}
/**
 * ��������
 * 
 * @param t
 * @return
 */
public Integer save(T t) {
     return this.mapper.insert(t);
}
/**
 * ѡ��Ϊnull���ֶ���Ϊ�������ݵ��ֶ�����������
 * 
 * @param t
 * @return
 */
public Integer saveSelective(T t) {
	return this.mapper.insertSelective(t);
}
/**
 * ��������
 * 
 * @param t
 * @return
 */
public Integer update(T t) {
	return this.mapper.updateByPrimaryKey(t);
}
/**
 * ѡ��Ϊnull���ֶ���Ϊ���µ��ֶ�����������
 * 
 * @param t
 * @return
 */
public Integer updateSelective(T t) {
	return this.mapper.updateByPrimaryKeySelective(t);
}
/**
 * ��������idɾ�����ݣ�����ɾ����
 * 
 * @param id
 * @return
 */
public Integer deleteById(Long id) {
	return this.mapper.deleteByPrimaryKey(id);
}
/**
 * ����ɾ������
 * 
 * @param ids
 * @param clazz
 * @param property ���ݿ��ֶ���
 * @return
 */
public Integer deleteByIds(List<Object> ids,Class<T> clazz	,String property) {
	Example example=new Example(clazz);
	example.createCriteria().andIn(property, ids);
	return this.mapper.deleteByExample(example);
}
/**
 * ��������ɾ������
 * 
 * @param record
 * @return
 */
public Integer deleteByWhere(T record) {
	return this.mapper.delete(record);
}
}
