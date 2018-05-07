package com.talent.dcs.util;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import com.talent.base.util.JacksonUtil;
import com.talent.dcs.dto.PhysicalExaminationRecordDto;

public class Test {
	public static void main(String[] args) throws Exception {
		String json = "{\"_619\":\"B\",\"_621\":\"56\",\"_623\":\"13970000032\",\"_624\":\"900132\",\"_625\":\"39824\",\"_626\":\"1\",\"_627\":\"A0032\"}";
		String json1 = "{\"_619\":\"B\",\"_621\":\"56\",\"_623\":\"13970000032\",\"_624\":\"900132\",\"_625\":\"39824\",\"_626\":\"1\",\"_1\":\"1\",\"_2\":\"1\",\"_627\":\"A0032\"}";

		PhysicalExaminationRecordDto physicalExaminationRecordDto = JacksonUtil.readValue(json,
				PhysicalExaminationRecordDto.class);
		PhysicalExaminationRecordDto physicalExaminationRecordDto1 = JacksonUtil.readValue(json1,
				PhysicalExaminationRecordDto.class);

		Class cla = physicalExaminationRecordDto.getClass();
		Class cla1 = physicalExaminationRecordDto1.getClass();
		int length = cla.getFields().length;

		System.out.println(length);
		for (int i = 1; i < length+1; i++) {
			try {
				Method method = cla.getMethod("get_" + i);
				Object object = method.invoke(physicalExaminationRecordDto);
				Method method1 = cla1.getMethod("get_" + i);
				Object object1 = method1.invoke(physicalExaminationRecordDto1);
				System.out.println(String.valueOf(object)+"   "+String.valueOf(object1));
				if (object == null && object1 != null) {
					method = cla.getMethod("set_" + i, String.class);
					method.invoke(physicalExaminationRecordDto, new Object[] { String.valueOf(object1) });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(JacksonUtil.toJSon(physicalExaminationRecordDto));

		// Method method = cla.getMethod("set" + name, String.class);
		// method.invoke(object, new Object[] { value });

	}
}
