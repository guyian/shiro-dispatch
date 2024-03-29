package com.hengxc.shiro.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hengxc.shiro.base.entity.Dept;
import com.hengxc.shiro.base.mapper.DeptMapper;
import com.hengxc.shiro.base.service.IDeptService;
import com.hengxc.shiro.common.entity.Constant;
import com.hengxc.shiro.common.entity.DeptTree;
import com.hengxc.shiro.common.entity.QueryRequest;
import com.hengxc.shiro.common.utils.SortUtil;
import com.hengxc.shiro.common.utils.TreeUtil;
import com.hengxc.shiro.common.utils.snowFlake.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author chenguangxu
 * @since 2019-07-25
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    SnowFlake snowFlake = new SnowFlake(1, 1);

    @Override
    public List<DeptTree<Dept>> findDepts() {
        List<Dept> depts = this.baseMapper.selectList(new QueryWrapper<>());
        List<DeptTree<Dept>> trees = this.convertDepts(depts);
        return TreeUtil.buildDeptTree(trees);
    }

    @Override
    public List<DeptTree<Dept>> findDepts(Dept dept) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(dept.getDeptName()))
            queryWrapper.lambda().eq(Dept::getDeptName, dept.getDeptName());
        queryWrapper.lambda().orderByAsc(Dept::getOrderNum);

        List<Dept> depts = this.baseMapper.selectList(queryWrapper);
        List<DeptTree<Dept>> trees = this.convertDepts(depts);
        return TreeUtil.buildDeptTree(trees);
    }

    @Override
    public List<Dept> findDepts(Dept dept, QueryRequest request) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(dept.getDeptName()))
            queryWrapper.lambda().eq(Dept::getDeptName, dept.getDeptName());
        SortUtil.handleWrapperSort(request, queryWrapper, "orderNum", Constant.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createDept(Dept dept) {
        if (dept.getDeptId() == null) {
            dept.setDeptId(snowFlake.nextId());
        }
        Long parentId = dept.getParentId();
        if (parentId == null)
            dept.setParentId(0L);
        dept.setCreateTime(Instant.now().toEpochMilli());
        this.save(dept);
    }

    @Override
    @Transactional
    public void updateDept(Dept dept) {
        dept.setModifyTime(Instant.now().toEpochMilli());
        this.baseMapper.updateById(dept);
    }

    @Override
    @Transactional
    public void deleteDepts(List<Long> deptIds) {
        deptIds.forEach(deptId -> {
                    //删除本部门
                    this.baseMapper.deleteByDeptId(deptId);
                    //递归删除子部门
                    this.deleteDeptById(deptId);
                }
        );
    }

    //递归删除部门信息
    public void deleteDeptById(Long deptIds) {
        List<Long> ids = this.baseMapper.findDeptIdByParentId(deptIds);
        if (ids.size() > 0) {
            ids.forEach(deptId -> {
                //删除本部门
                this.baseMapper.deleteByDeptId(deptId);
                deleteDeptById(deptId);
            });
        }
    }

    private List<DeptTree<Dept>> convertDepts(List<Dept> depts) {
        List<DeptTree<Dept>> trees = new ArrayList<>();
        depts.forEach(dept -> {
            DeptTree<Dept> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getDeptId()));
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setName(dept.getDeptName());
            tree.setData(dept);
            trees.add(tree);
        });
        return trees;
    }


}
