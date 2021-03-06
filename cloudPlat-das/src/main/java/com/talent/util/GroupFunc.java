package com.talent.util;

import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Row;

import com.google.common.collect.Lists;
import com.talent.job.serial.GroupDetails;
import com.talent.job.serial.RowKey;

import scala.Tuple2;

public class GroupFunc {
    // ����
    public static JavaPairRDD<RowKey, Iterable<Row>> staticsGroup(JavaPairRDD<String, Row> rdd,
            final GroupDetails groupDetails) {
        JavaPairRDD<String, Iterable<Row>> pRdd = rdd.groupByKey();

        JavaPairRDD<RowKey, Iterable<Row>> pRdd1 = pRdd
                .mapToPair(new PairFunction<Tuple2<String, Iterable<Row>>, RowKey, Iterable<Row>>() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public Tuple2<RowKey, Iterable<Row>> call(Tuple2<String, Iterable<Row>> t) throws Exception {

                        RowKey rowKey = new RowKey();

                        Iterator<Row> iters = t._2.iterator();
                        int index = 0;
                        List<Row> list = Lists.newArrayList();
                        while (iters.hasNext()) {
                            Row next = iters.next();
                            if (MRUtils.B_TRUE.equals(MRUtils.filtersFeature(next, groupDetails.saveFeatrueMap,
                                    groupDetails.saveDiseaseMap, groupDetails.saveItemMap,
                                    groupDetails.saveRelationStr))) {

                                index++;
                                list.add(next);
                                continue;

                            }
                        }
                        rowKey.sum = index;
                        return new Tuple2<RowKey, Iterable<Row>>(rowKey, list);
                    }

                });

        pRdd1 = pRdd1.filter(new Function<Tuple2<RowKey, Iterable<Row>>, Boolean>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Boolean call(Tuple2<RowKey, Iterable<Row>> t) throws Exception {

                if (t._1.sum < 1)
                    return false;
                return true;
            }
        });

        return pRdd1;
    }

}
