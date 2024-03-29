package com.hengxc.shiro.base.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hengxc.shiro.base.entity.Menu;
import com.hengxc.shiro.base.entity.User;
import com.hengxc.shiro.base.service.IMenuService;
import com.hengxc.shiro.common.annotation.Log;
import com.hengxc.shiro.common.entity.MenuTree;
import com.hengxc.shiro.common.entity.Response;
import com.hengxc.shiro.common.exception.FebsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;

import static com.hengxc.shiro.common.utils.FebsUtil.getCurrentUser;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author chenguangxu
 * @since 2019-07-25
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController {


    @Autowired
    private IMenuService menuService;

    @GetMapping("{username}")
    public Response getUserMenus(@NotBlank(message = "{required}") @PathVariable String username) throws FebsException {
        User currentUser = getCurrentUser();
        if (!StringUtils.equalsIgnoreCase(username, currentUser.getUsername()))
            throw new FebsException("您无权获取别人的菜单");
        MenuTree<Menu> userMenus = this.menuService.findUserMenus(username);
        return new Response().data(userMenus);
    }

    @GetMapping("tree")
    public Response getMenuTree(Menu menu) throws FebsException {
        try {
            MenuTree<Menu> menus = this.menuService.findMenus(menu);
            return new Response().success().data(menus.getChilds());
        } catch (java.lang.Exception e) {
            String message = "获取菜单树失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("新增菜单/按钮")
    @PostMapping
    @RequiresPermissions("menu:add")
    public Response addMenu(@Valid Menu menu) throws FebsException {
        try {
            this.menuService.createMenu(menu);
            return new Response().success();
        } catch (java.lang.Exception e) {
            String message = "新增菜单/按钮失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除菜单/按钮")
    @GetMapping("delete/{menuIds}")
    @RequiresPermissions("menu:delete")
    public Response deleteMenus(@NotBlank(message = "{required}") @PathVariable String menuIds) throws FebsException {
        try {
            String[] ids = menuIds.split(StringPool.COMMA);
            Long[] strToLong = (Long[]) ConvertUtils.convert(ids, Long.class);
            List<Long> listIds = Arrays.asList(strToLong);
            this.menuService.deleteMeuns(listIds);
            return new Response().success();
        } catch (java.lang.Exception e) {
            String message = "删除菜单/按钮失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改菜单/按钮")
    @PostMapping("update")
    @RequiresPermissions("menu:update")
    public Response updateMenu(@Valid Menu menu) throws FebsException {
        try {
            this.menuService.updateMenu(menu);
            return new Response().success();
        } catch (java.lang.Exception e) {
            String message = "修改菜单/按钮失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }


}
