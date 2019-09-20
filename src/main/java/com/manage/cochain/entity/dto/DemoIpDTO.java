package com.manage.cochain.entity.dto;

import com.manage.cochain.entity.po.DemoIpDO;

/**
 * 远程网址端口信息 传输类
 * @author wzx
 * @create 2019年05月17日 09:31:55
**/
public class DemoIpDTO extends DemoIpDO {

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
