package com.manage.cochain.entity.dto;

import com.manage.cochain.entity.po.DemoUrlDO;

/**
 * 远程接口上链路径信息 传输类
 * @author wzx
 * @create 2019年05月17日 09:34:37
**/
public class DemoUrlDTO extends DemoUrlDO {

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
