package com.manage.cochain.entity.dto;

import com.manage.cochain.entity.po.BasePoolDO;

/**
 * 基础数据池 传输类
 * @author wzx
 * @create 2019年08月22日 10:21:07
**/
public class BasePoolDTO extends BasePoolDO {

        private int start;
        private int end;

        public int getStart() {
        return start;
        }

        public void setStart(int start) {
        this.start = start;
        }

        public int getEnd() {
        return end;
        }

        public void setEnd(int end) {
        this.end = end;
        }
        @Override
        public String toString() {
            return super.toString();
        }
}
