<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--
-->

<mapper namespace="com.isoftstone.hig.oms.repository.OmsOrderGoodsRepository">
    <resultMap id="OmsOrderGoodsMap" type="com.isoftstone.hig.oms.api.entity.OmsOrderGoods">
        <id column="KEY_ID" jdbcType="VARCHAR" property="keyId" javaType="String"/>
        <result column="CREATED_BY" jdbcType="VARCHAR" property="createdBy" javaType="String"/>
        <result column="CREATED_TIME" jdbcType="TIMESTAMP" property="createdTime" javaType="java.sql.Timestamp"/>
        <result column="GOODS_ID_OUTER" jdbcType="VARCHAR" property="goodsIdOuter" javaType="String"/>
        <result column="ITEM1" jdbcType="VARCHAR" property="item1" javaType="String"/>
        <result column="ITEM2" jdbcType="VARCHAR" property="item2" javaType="String"/>
        <result column="ITEM3" jdbcType="VARCHAR" property="item3" javaType="String"/>
        <result column="ITEM4" jdbcType="VARCHAR" property="item4" javaType="String"/>
        <result column="NUM" jdbcType="NUMERIC" property="num" javaType="java.math.BigDecimal"/>
        <result column="NUM_REAL" jdbcType="NUMERIC" property="numReal" javaType="java.math.BigDecimal"/>
        <result column="RF_ORDER_ID" jdbcType="VARCHAR" property="rfOrderId" javaType="String"/>
        <result column="RF_SOURCE_FROM" jdbcType="VARCHAR" property="rfSourceFrom" javaType="String"/>
        <result column="TERMINAL_CONSUMER" jdbcType="VARCHAR" property="terminalConsumer" javaType="String"/>
        <result column="UPDATED_BY" jdbcType="VARCHAR" property="updatedBy" javaType="String"/>
        <result column="UPDATED_TIME" jdbcType="TIMESTAMP" property="updatedTime" javaType="java.sql.Timestamp"/>
        <result column="VERSION_CODE" jdbcType="VARCHAR" property="versionCode" javaType="String"/>
        <result column="RF_GOODS_ID" jdbcType="VARCHAR" property="rfGoodsId" javaType="String"/>
        <result column="OUT_ORDER_ID" jdbcType="VARCHAR" property="outOrderId" javaType="String"/>

        <result column="RF_RECEIVE_WAREHOUSE_ID" jdbcType="VARCHAR" property="rfReceiveWarehouseId" javaType="String"/>
        <result column="RECEIVE_COMPANY" jdbcType="VARCHAR" property="receiveCompany" javaType="String"/>
        <result column="RECEIVE_NAME" jdbcType="VARCHAR" property="receiveName" javaType="String"/>
        <result column="RECEIVE_PHONE" jdbcType="VARCHAR" property="receivePhone" javaType="String"/>
        <result column="RECEIVE_COUNTRY" jdbcType="VARCHAR" property="receiveCountry" javaType="String"/>
        <result column="RECEIVE_PROVINCE" jdbcType="VARCHAR" property="receiveProvince" javaType="String"/>
        <result column="RECEIVE_CITY" jdbcType="VARCHAR" property="receiveCity" javaType="String"/>
        <result column="RECEIVE_AREA" jdbcType="VARCHAR" property="receiveArea" javaType="String"/>
        <result column="RECEIVE_ADDRESS" jdbcType="VARCHAR" property="receiveAddress" javaType="String"/>

    </resultMap>

    <!-- 要使用这个SQL 标签，则参数名必须要为 "model" 否则这里面的 model.createBy 这样的对象就拿不到数据。会报错的 -->
    <sql id="updateModelSetBlock">
        <set>
            <if test="model != null">
                <if test="model.createdBy != null">
                    CREATED_BY= #{model.createdBy,jdbcType=VARCHAR},
                </if>
                <if test="model.createdTime != null">
                    CREATED_TIME= #{model.createdTime,jdbcType=TIMESTAMP},
                </if>
                <if test="model.goodsIdOuter != null">
                    GOODS_ID_OUTER= #{model.goodsIdOuter,jdbcType=VARCHAR},
                </if>
                <if test="model.item1 != null">
                    ITEM1= #{model.item1,jdbcType=VARCHAR},
                </if>
                <if test="model.item2 != null">
                    ITEM2= #{model.item2,jdbcType=VARCHAR},
                </if>
                <if test="model.item3 != null">
                    ITEM3= #{model.item3,jdbcType=VARCHAR},
                </if>
                <if test="model.item4 != null">
                    ITEM4= #{model.item4,jdbcType=VARCHAR},
                </if>
                <if test="model.num != null">
                    NUM= #{model.num,jdbcType=NUMERIC},
                </if>
                <if test="model.numReal != null">
                    NUM_REAL= #{model.numReal,jdbcType=NUMERIC},
                </if>
                <if test="model.rfOrderId != null">
                    RF_ORDER_ID= #{model.rfOrderId,jdbcType=VARCHAR},
                </if>
                <if test="model.rfSourceFrom != null">
                    RF_SOURCE_FROM= #{model.rfSourceFrom,jdbcType=VARCHAR},
                </if>
                <if test="model.terminalConsumer != null">
                    TERMINAL_CONSUMER= #{model.terminalConsumer,jdbcType=VARCHAR},
                </if>
                <if test="model.updatedBy != null">
                    UPDATED_BY= #{model.updatedBy,jdbcType=VARCHAR},
                </if>

                <if test="model.versionCode != null">
                    VERSION_CODE= #{model.versionCode,jdbcType=VARCHAR},
                </if>
                <if test="model.rfGoodsId != null">
                    RF_GOODS_ID= #{model.rfGoodsId,jdbcType=VARCHAR},
                </if>

                <if test="model.rfReceiveWarehouseId != null">
                    RF_RECEIVE_WAREHOUSE_ID= #{model.rfReceiveWarehouseId,jdbcType=VARCHAR},
                </if>
                <if test="model.receiveCompany != null">
                    RECEIVE_COMPANY= #{model.receiveCompany,jdbcType=VARCHAR},
                </if>
                <if test="model.receiveName != null">
                    RECEIVE_NAME= #{model.receiveName,jdbcType=VARCHAR},
                </if>
                <if test="model.receivePhone != null">
                    RECEIVE_PHONE= #{model.receivePhone,jdbcType=VARCHAR},
                </if>
                <if test="model.receiveCountry != null">
                    RECEIVE_COUNTRY= #{model.receiveCountry,jdbcType=VARCHAR},
                </if>
                <if test="model.receiveProvince != null">
                    RECEIVE_PROVINCE= #{model.receiveProvince,jdbcType=VARCHAR},
                </if>
                <if test="model.receiveCity != null">
                    RECEIVE_CITY= #{model.receiveCity,jdbcType=VARCHAR},
                </if>
                <if test="model.receiveArea != null">
                    RECEIVE_AREA= #{model.receiveArea,jdbcType=VARCHAR},
                </if>
                <if test="model.receiveAddress != null">
                    RECEIVE_ADDRESS= #{model.receiveAddress,jdbcType=VARCHAR},
                </if>
                UPDATED_TIME= #{model.updatedTime,jdbcType=TIMESTAMP}
            </if>
        </set>
    </sql>

    <!-- 字段的顺序必须要和字段值的顺序一致 -->
    <sql id="insertAllFields">
        CREATED_BY,CREATED_TIME,GOODS_ID_OUTER,ITEM1,ITEM2,ITEM3,ITEM4,KEY_ID,NUM,NUM_REAL,RF_ORDER_ID,RF_SOURCE_FROM,TERMINAL_CONSUMER,UPDATED_BY,UPDATED_TIME,VERSION_CODE,RF_GOODS_ID,
        RF_RECEIVE_WAREHOUSE_ID,RECEIVE_COMPANY,RECEIVE_NAME,RECEIVE_PHONE,RECEIVE_COUNTRY,RECEIVE_PROVINCE,RECEIVE_CITY,RECEIVE_AREA,RECEIVE_ADDRESS
    </sql>
    <!-- 字段对象名必须叫 "model" 要通过他来取值 -->
    <sql id="insertAllValuesForModel">
        #{model.createdBy,jdbcType=VARCHAR},#{model.createdTime,jdbcType=TIMESTAMP},#{model.goodsIdOuter,jdbcType=VARCHAR},#{model.item1,jdbcType=VARCHAR},#{model.item2,jdbcType=VARCHAR},#{model.item3,jdbcType=VARCHAR},#{model.item4,jdbcType=VARCHAR},#{model.keyId,jdbcType=VARCHAR},#{model.num,jdbcType=NUMERIC},#{model.numReal,jdbcType=NUMERIC},#{model.rfOrderId,jdbcType=VARCHAR},#{model.rfSourceFrom,jdbcType=VARCHAR},#{model.terminalConsumer,jdbcType=VARCHAR},#{model.updatedBy,jdbcType=VARCHAR},#{model.updatedTime,jdbcType=TIMESTAMP},#{model.versionCode,jdbcType=VARCHAR},#{model.rfGoodsId,jdbcType=VARCHAR}
        ,#{model.rfReceiveWarehouseId,jdbcType=VARCHAR},#{model.receiveCompany,jdbcType=VARCHAR},#{model.receiveName,jdbcType=VARCHAR},#{model.receivePhone,jdbcType=VARCHAR},#{model.receiveCountry,jdbcType=VARCHAR},#{model.receiveProvince,jdbcType=VARCHAR},#{model.receiveCity,jdbcType=VARCHAR},#{model.receiveArea,jdbcType=VARCHAR},#{model.receiveAddress,jdbcType=VARCHAR}
    </sql>
    <insert id="add" parameterType="com.isoftstone.hig.oms.api.entity.OmsOrderGoods">
        insert into oms_order_goods( <include refid="insertAllFields"></include> )
        values ( <include refid="insertAllValuesForModel"></include> )
    </insert>

    <!-- 这里是oracle 批量插入的SQL，跟MYSQL 会不一致 -->
    <!-- 在这里指定参数为List 集合 -->
    <insert id="addModels" parameterType="java.util.List">
        insert all
        <!-- collection="models" 这里匹配的是接口的参数名: boolean addModels(@Param("models") List<OmsOrderGoods> models);-->
        <!-- 这里的 item="model" 是自定义的名称，它主要用在foreach 中的 集合元素上，给这个集合的元素一个命名 这样在这个匹配字段的时候 就可以使用  model.createBy 这样的方式获取到值 -->
        <foreach collection="models" item="model">
            into oms_order_goods( <include refid="insertAllFields"></include> )
            values (<include refid="insertAllValuesForModel"></include>)
        </foreach>
        select 1 from dual
    </insert>

    <update id="updateModelForKeyId" parameterType="com.isoftstone.hig.oms.api.entity.OmsOrderGoods">
        update oms_order_goods
        <include refid="updateModelSetBlock"></include>
        where KEY_ID = #{model.keyId} AND DEL_FLAG != '1'
    </update>
    <update id="updateRfSourceFrom">
        update oms_order_goods
        set contract_category = #{rfsourcefrom, jdbcType=VARCHAR}
        WHERE KEY_ID = #{keyid} AND DEL_FLAG != '1'
    </update>


    <update id="delete" parameterType="String">
        UPDATE OMS_ORDER_GOODS
        SET DEL_FLAG = '1'
        WHERE KEY_ID = #{keyId}
    </update>

    <update id="deleteByRfOrderId">
        UPDATE OMS_ORDER_GOODS
        SET DEL_FLAG = '1'
        WHERE RF_ORDER_ID = #{rfOrderId}
    </update>

   <!--批量修改-->
    <update id="updateOutPlan" parameterType="java.util.List">
        <foreach collection="list" separator=";" item="item" open="begin" close=";end;">
            update oms_order_goods
            set NUM_REAL = #{item.numReal, jdbcType=VARCHAR}
            WHERE KEY_ID = #{item.keyId}
        </foreach>

    </update>

    <select id="getModel" parameterType="String" resultType="com.isoftstone.hig.oms.api.entity.OmsOrderGoods">
        select CREATED_BY,
               CREATED_TIME,
               GOODS_ID_OUTER,
               ITEM1,
               ITEM2,
               ITEM3,
               ITEM4,
               KEY_ID,
               NUM,
               NUM_REAL,
               RF_ORDER_ID,
               RF_SOURCE_FROM,
               TERMINAL_CONSUMER,
               UPDATED_BY,
               UPDATED_TIME,
               VERSION_CODE,
               RF_GOODS_ID,

               RF_RECEIVE_WAREHOUSE_ID,
               RECEIVE_COMPANY,
               RECEIVE_NAME,
               RECEIVE_PHONE,
               RECEIVE_COUNTRY,
               RECEIVE_PROVINCE,
               RECEIVE_CITY,
               RECEIVE_AREA,
               RECEIVE_ADDRESS
        from oms_order_goods
        where KEY_ID = #{keyId} AND DEL_FLAG != '1'
    </select>

    <select id="getListArray" parameterType="String" resultType="com.isoftstone.hig.oms.api.entity.OmsOrderGoods">
        select CREATED_BY,
               CREATED_TIME,
               GOODS_ID_OUTER,
               ITEM1,
               ITEM2,
               ITEM3,
               ITEM4,
               KEY_ID,
               NUM,
               NUM_REAL,
               RF_ORDER_ID,
               RF_SOURCE_FROM,
               TERMINAL_CONSUMER,
               UPDATED_BY,
               UPDATED_TIME,
               VERSION_CODE,
               RF_GOODS_ID,

               RF_RECEIVE_WAREHOUSE_ID,
               RECEIVE_COMPANY,
               RECEIVE_NAME,
               RECEIVE_PHONE,
               RECEIVE_COUNTRY,
               RECEIVE_PROVINCE,
               RECEIVE_CITY,
               RECEIVE_AREA,
               RECEIVE_ADDRESS
        from oms_order_goods
        where 1 = 1
          and #{whereStr} AND DEL_FLAG != '1'
    </select>

    <select id="getListByRfOrderId" resultType="com.isoftstone.hig.oms.api.entity.OmsOrderGoods">
        select CREATED_BY,
               CREATED_TIME,
               GOODS_ID_OUTER,
               ITEM1,
               ITEM2,
               ITEM3,
               ITEM4,
               KEY_ID,
               NUM,
               NUM_REAL,
               RF_ORDER_ID,
               RF_SOURCE_FROM,
               TERMINAL_CONSUMER,
               UPDATED_BY,
               UPDATED_TIME,
               VERSION_CODE,
               OUT_ORDER_ID,
               RF_GOODS_ID,

               RF_RECEIVE_WAREHOUSE_ID,
               RECEIVE_COMPANY,
               RECEIVE_NAME,
               RECEIVE_PHONE,
               RECEIVE_COUNTRY,
               RECEIVE_PROVINCE,
               RECEIVE_CITY,
               RECEIVE_AREA,
               RECEIVE_ADDRESS
        from oms_order_goods
        where 1 = 1
          and RF_ORDER_ID = #{rfOrderId} AND DEL_FLAG != '1'
    </select>


    <select id="pagingHelperOmsOrderGoods" parameterType="com.isoftstone.hig.oms.api.filter.OmsOrderGoodsFilter"
            resultType="com.isoftstone.hig.oms.api.entity.OmsOrderGoods">
        select
        CREATED_BY,CREATED_TIME,GOODS_ID_OUTER,ITEM1,ITEM2,ITEM3,ITEM4,KEY_ID,NUM,NUM_REAL,RF_ORDER_ID,RF_SOURCE_FROM,TERMINAL_CONSUMER,UPDATED_BY,UPDATED_TIME,VERSION_CODE,RF_GOODS_ID,OUT_ORDER_ID,RF_RECEIVE_WAREHOUSE_ID,RECEIVE_COMPANY,RECEIVE_NAME,RECEIVE_PHONE,RECEIVE_COUNTRY,RECEIVE_PROVINCE,RECEIVE_CITY,RECEIVE_AREA,RECEIVE_ADDRESS
        from oms_order_goods where 1=1 AND DEL_FLAG != '1'
        <if test="createdBy != null and createdBy!= ''">
            AND CREATED_BY = #{createdBy}
        </if>
        <if test="createdTime != null and createdTime!= ''">
            AND CREATED_TIME = #{createdTime}
        </if>
        <if test="goodsIdOuter != null and goodsIdOuter!= ''">
            AND GOODS_ID_OUTER = #{goodsIdOuter}
        </if>
        <if test="item1 != null and item1!= ''">
            AND ITEM1 = #{item1}
        </if>
        <if test="item2 != null and item2!= ''">
            AND ITEM2 = #{item2}
        </if>
        <if test="item3 != null and item3!= ''">
            AND ITEM3 = #{item3}
        </if>
        <if test="item4 != null and item4!= ''">
            AND ITEM4 = #{item4}
        </if>
        <if test="keyId != null and keyId!= ''">
            AND KEY_ID = #{keyId}
        </if>
        <if test="num != null and num!= ''">
            AND NUM = #{num}
        </if>
        <if test="numReal != null and numReal!= ''">
            AND NUM_REAL = #{numReal}
        </if>
        <if test="rfOrderId != null and rfOrderId!= ''">
            AND RF_ORDER_ID = #{rfOrderId}
        </if>
        <if test="rfSourceFrom != null and rfSourceFrom!= ''">
            AND RF_SOURCE_FROM = #{rfSourceFrom}
        </if>
        <if test="terminalConsumer != null and terminalConsumer!= ''">
            AND TERMINAL_CONSUMER = #{terminalConsumer}
        </if>
        <if test="updatedBy != null and updatedBy!= ''">
            AND UPDATED_BY = #{updatedBy}
        </if>
        <if test="updatedTime != null and updatedTime!= ''">
            AND UPDATED_TIME = #{updatedTime}
        </if>
        <if test="versionCode != null and versionCode!= ''">
            AND VERSION_CODE = #{versionCode}
        </if>
        <if test="rfGoodsId != null and rfGoodsId!= ''">
            AND RF_GOODS_ID = #{rfGoodsId}
        </if>

        <if test="rfReceiveWarehouseId != null and rfReceiveWarehouseId!= ''">
            AND RF_RECEIVE_WAREHOUSE_ID = #{rfReceiveWarehouseId}
        </if>
        <if test="receiveCompany != null and receiveCompany!= ''">
            AND RECEIVE_COMPANY = #{receiveCompany}
        </if>
        <if test="receiveName != null and receiveName!= ''">
            AND RECEIVE_NAME = #{receiveName}
        </if>
        <if test="receivePhone != null and receivePhone!= ''">
            AND RECEIVE_PHONE = #{receivePhone}
        </if>
        <if test="receiveCountry != null and receiveCountry!= ''">
            AND RECEIVE_COUNTRY = #{receiveCountry}
        </if>
        <if test="receiveProvince != null and receiveProvince!= ''">
            AND RECEIVE_PROVINCE = #{receiveProvince}
        </if>
        <if test="receiveCity != null and receiveCity!= ''">
            AND RECEIVE_CITY = #{receiveCity}
        </if>
        <if test="receiveArea != null and receiveArea!= ''">
            AND RECEIVE_AREA = #{receiveArea}
        </if>
        <if test="receiveAddress != null and receiveAddress!= ''">
            AND RECEIVE_ADDRESS = #{receiveAddress}
        </if>
    </select>

    <!--调用存储过程分页
    <select id="pagingOmsOrderGoods" parameterType="com.isoftstone.hig.common.model.PagingProceduresInfo" resultType="com.isoftstone.hig.oms.api.entity.OmsOrderGoods"  statementType="CALLABLE">
        { call Proc_CommonPagingStoredProcedure(
                #{tables,mode=IN,jdbcType=VARCHAR},
                #{pk,mode=IN,jdbcType=VARCHAR},
                #{sort, mode=IN, jdbcType=VARCHAR},
                #{pageNumber, mode=IN, jdbcType=INTEGER},
                #{pageSize, mode=IN, jdbcType=INTEGER},
                #{fields, mode=IN, jdbcType=VARCHAR},
                #{filter, mode=IN, jdbcType=VARCHAR},
                #{countTotal, mode=IN, jdbcType=BIT},
                #{total, mode=OUT, jdbcType=INTEGER}) }
    </select>
    -->

    <select id="getListByRfOrderIdWL" resultMap="OmsOrderGoodsMap">
        select CREATED_BY,
               CREATED_TIME,
               GOODS_ID_OUTER,
               ITEM1,
               ITEM2,
               ITEM3,
               ITEM4,
               KEY_ID,
               NUM,
               NUM_REAL,
               RF_ORDER_ID,
               RF_SOURCE_FROM,
               TERMINAL_CONSUMER,
               UPDATED_BY,
               UPDATED_TIME,
               VERSION_CODE,
               RF_GOODS_ID,
               KEY_ID,
               OUT_ORDER_ID
        from oms_order_goods
        where 1 = 1
          and
        KEY_ID in
          <foreach item="omsOutgoingPlan" index="index" collection="param" open="(" separator="," close=")">
        #{omsOutgoingPlan.rfKeyId,jdbcType=VARCHAR}
    </foreach>
           for update
    </select>

    <select id="updateRfSourceFromAndOrder" resultMap="OmsOrderGoodsMap">
        select CREATED_BY,
               CREATED_TIME,
               GOODS_ID_OUTER,
               ITEM1,
               ITEM2,
               ITEM3,
               ITEM4,
               KEY_ID,
               NUM,
               NUM_REAL,
               RF_ORDER_ID,
               RF_SOURCE_FROM,
               TERMINAL_CONSUMER,
               UPDATED_BY,
               UPDATED_TIME,
               VERSION_CODE,
               RF_GOODS_ID,
               OUT_ORDER_ID
        from oms_order_goods
        where 1 = 1
          and RF_ORDER_ID = #{orderId}  and RF_SOURCE_FROM = #{rfsourcefrom}
    </select>
</mapper>