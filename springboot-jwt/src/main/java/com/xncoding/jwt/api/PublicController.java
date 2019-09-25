package com.xncoding.jwt.api;

import com.xncoding.jwt.api.model.BaseResponse;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
 * 机具管理API接口类
 */
@Api(value = "机具管理业务接口类", tags = "机具管理", description = "机具管理业务接口类")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")}
)
@RestController
@RequestMapping(value = "/api/v1")
public class PublicController {

    private static final Logger _logger = LoggerFactory.getLogger(PublicController.class);

    /**
     * 入网绑定查询接口
     *
     * @return 是否入网
     */
    @ApiOperation(value = "入网绑定查询接口", notes = "根据IMEI码来查询该POS机是否入网并绑定了网点<br/>每次打开APP的时候需要调用这个接口来进行检查<br/>如果入网并且绑定了网点才能打开APP，否则必须调用入网或绑定网点接口成功后才能继续下一步", produces = "application/json")
    @ApiImplicitParam(name = "imei", value = "IMEI码", required = true, dataType = "String", paramType = "query", defaultValue = "1234555SHA")
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    @RequiresAuthentication
    public BaseResponse join(@ApiParam(value = "IMEI码") @RequestParam("imei") String imei) {
        _logger.info("入网查询接口 start... imei=" + imei);
        BaseResponse result = new BaseResponse();
        result.setSuccess(true);
        result.setMsg("已入网并绑定了网点");
        return result;
    }
}
