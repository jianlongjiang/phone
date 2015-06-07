package com.phone.cn.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.phone.cn.bean.SearchBean;
import com.phone.cn.entity.search.Searchable;


@NoRepositoryBean
public interface BaseMapper<M, ID extends Serializable> extends Repository<M, ID> {

    int deleteByPrimaryKey(ID id);

    int insert(M record);

    int insertSelective(M record);

    M selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(M record);

//    int updateByPrimaryKey(M record);
    
    List<M> findByIds(@Param("ids") List<ID> ids);
    
    List<M> findBySelective(M cc);
    
    List<M> query(SearchBean cc);
    
    PageList<M> query(SearchBean searchBean, PageBounds pageBounds);
    
    

    /**
     * Returns whether an entity with the given id exists.
     * 
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    boolean exists(ID id);
    
    /**
     * Returns the number of entities available.
     * 
     * @return the number of entities
     */
    long count();
    
    /**
     * 根据主键删除
     *
     * @param ids
     */
    public void delete(ID[] ids);


    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
     */
    List<M> findAll(Sort sort);


    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable
     * @return a page of entities
     */
    Page<M> findAll(Pageable pageable);

    /**
     * 根据条件查询所有
     * 条件 + 分页 + 排序
     *
     * @param searchable
     * @return
     */
    public Page<M> findAll(Searchable searchable);


    /**
     * 根据条件统计所有记录数
     *
     * @param searchable
     * @return
     */
    public long count(Searchable searchable);

}
