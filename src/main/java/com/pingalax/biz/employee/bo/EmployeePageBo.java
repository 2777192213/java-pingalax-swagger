package com.pingalax.biz.employee.bo;

import com.pingalax.common.util.page.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.naming.Name;
import java.io.PrintStream;

/**
 * @author zhouxiaotao
 * @Description: 用户分页查找类
 * @date 2023-09-08 13:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeePageBo extends BasePage {
    private String name;
    private Integer status;
}
