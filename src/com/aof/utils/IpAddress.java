package com.aof.utils;

public class IpAddress {
    private int[] address = new int[4];

    public int getAddress(int index) {
        return this.address[index];
    }

    public IpAddress(String addressString) throws Exception {
        String[] part = addressString.split("[.]");
        if (part.length != this.address.length)
            throw new Exception("Error ip address format");
        for (int i = 0; i < this.address.length; i++)
            this.address[i] = Integer.parseInt(part[i]);
    }

    public int compareTo(IpAddress anotherIpAddress) {
        for (int index = 0; index < this.address.length; index++) {
            int dif = getAddress(index) - anotherIpAddress.getAddress(index);
            if (dif != 0)
                return dif;
        }
        return 0;
    }

    public boolean equals(Object rhs) {
        if (rhs == null)
            return false;
        if (this == rhs)
            return true;
        if (!(rhs instanceof IpAddress))
            return false;
        IpAddress that = (IpAddress)rhs;
        for (int index = 0; index < this.address.length; index++) {
            if (getAddress(index) != that.getAddress(index))
                return false;
        }
        return true;
    }

    public String toString() {
        String str = "";
        int i = this.address[0];
        for (int index = 1; index < this.address.length; index++)
            str = String.valueOf(i) + "." + this.address[index];
        return str;
    }
}
