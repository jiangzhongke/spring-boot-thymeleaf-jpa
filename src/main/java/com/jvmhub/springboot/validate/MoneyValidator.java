package com.jvmhub.springboot.validate;


import java.math.BigDecimal;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class MoneyValidator implements ConstraintValidator<Money, BigDecimal> {

   private String moneyReg = "^\\d+(\\.\\d{1,2})?$";//表示金额的正则表达式
   private Pattern moneyPattern = Pattern.compile(moneyReg);
  
   public void initialize(Money money) {
      // TODO Auto-generated method stub
     
   }

   public boolean isValid(BigDecimal value, ConstraintValidatorContext arg1) {
      // TODO Auto-generated method stub
      if (value == null)
          return true;
      return moneyPattern.matcher(value.toString()).matches();
   }

}