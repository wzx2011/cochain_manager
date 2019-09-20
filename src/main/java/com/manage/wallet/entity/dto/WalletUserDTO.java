package com.manage.wallet.entity.dto;

import com.manage.wallet.entity.po.WalletUserDO;

/**
 * 钱包用户信息 传输类
 * @author wzx
 * @create 2019年06月12日 13:50:10
**/
public class WalletUserDTO extends WalletUserDO {

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
