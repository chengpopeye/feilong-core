/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.core.util;

import java.util.Iterator;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.feilong.core.Validator;

/**
 * {@link Iterator} 工具类.
 * 
 * <h3>迭代器{@link Iterator}与枚举有两点不同:</h3>
 * 
 * <blockquote>
 * <ol>
 * <li>迭代器{@link Iterator}允许调用者利用定义良好的语义在迭代期间从迭代器所指向的 collection 移除元素。</li>
 * <li>方法名称得到了改进。</li>
 * </ol>
 * </blockquote>
 *
 * @author feilong
 * @see org.apache.commons.collections4.IteratorUtils
 * @since 1.5.3
 */
public final class IteratorUtil{

    /** Don't let anyone instantiate this class. */
    private IteratorUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 迭代{@link Iterator},判断元素的字符串格式是否和传入的值<code>value</code>的字符串格式相等.
     * 
     * <p style="color:red">
     * 注意,比较的是{@link java.util.Objects#toString(Object, String)},常用于自定义标签或者el function
     * </p>
     * 
     * @param iterator
     *            iterator
     * @param value
     *            value
     * @return 如果iterator为null/empty,则返回false<br>
     *         否则迭代 <code>iterator</code>,将元素转成String,和传入参数 <code>value</code>的String值进行比较,如果相等直接返回true
     * @see Iterator#hasNext()
     * @see Iterator#next()
     * @see "org.springframework.util.CollectionUtils#contains(Iterator, Object)"
     * @see org.apache.commons.collections4.IteratorUtils#contains(Iterator, Object)
     */
    public static boolean containsByStringValue(Iterator<?> iterator,Object value){
        if (Validator.isNullOrEmpty(iterator)){
            return false;
        }
        while (iterator.hasNext()){
            Object object = iterator.next();
            //注意:如果null,java.util.Objects#toString(Object),返回 "null" 和  java.lang.String#valueOf(Object) 一样
            //而 org.apache.commons.lang3.ObjectUtils#toString(Object) 返回  ""  empty

            //如果发现有equals 的,那么就直接返回true
            if (Objects.toString(object, StringUtils.EMPTY).equals(Objects.toString(value, StringUtils.EMPTY))){
                return true;
            }
        }
        return false;
    }
}