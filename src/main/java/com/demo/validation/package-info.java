/**
 *	在pojo中定义校验规则,而pojo是被多个Controller所共享,当不同的Controller方法对同一个pojo进行校验,
 *	但是每一个controller都具有不同的校验规则,这时候就需要分组校验.
 *
 *	解决办法:  定义多个校验分组(其实就是一个java接口),标识不同的校验规则.每个Controller方法使用不同的校验分组规则.
 *			
 */
package com.demo.validation;