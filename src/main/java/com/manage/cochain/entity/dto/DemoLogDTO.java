package com.manage.cochain.entity.dto;

import com.manage.cochain.entity.po.DemoLogDO;

/**
 * 上链日志信息 传输类
 * @author wzx
 * @create 2019年05月22日 17:17:05
**/
public class DemoLogDTO extends DemoLogDO {

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
