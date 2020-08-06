package com.walor.javatest;

/**
 * <pre>
 *     author : walorwang
 *     time   : 2020/07/16
 *     desc   : 力扣真题练习
 * </pre>
 */
class LeetCodeCombat {

    /**
     * 例题 1：删除排序数组中的重复项
     * 例如，给定数组 nums = [1,1,2]，函数应该返回新的长度 2。
     * 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 显然这是一个数据去重的问题。
     */
//    public static void main(String[] args) {
//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        int temp = nums[0];
//        int len = 1;
//        for (int i = 1, sourceLen = nums.length; i < sourceLen; i++) {
//            if (nums[i] != temp) {
//                nums[len] = nums[i];
//                temp = nums[i];
//                len++;
//            }
//        }
//        System.out.println(len);
//        for (int i = 0; i < len; i++) {
//            System.out.println(nums[i]);
//        }
//    }

    /**
     * 例题 2：查找两个有序数组合并后的中位数
     * 要求算法的时间复杂度为 O(log(m + n))。
     * 如果 C 部分数字更少为 p 个，则剔除 C 部分；
     * 并只剔除 D 部分中的 p 个数字。
     * 这样就能保证，经过一次二分后，剔除之后的数组的中位数不变。
     * 终止条件：查找范围应该是一个大数组和一个只有 1～2 个元素的小数组。
     */
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {6, 7, 8, 9};
        int median = getMedian(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1);
        System.out.println(median);
    }

    public static int getMedian(int[] a, int begina, int enda, int[] b, int beginb, int endb) {
        if (enda - begina == 0) {
            return a[begina] > b[beginb] ? b[beginb] : a[begina];
        }
        if (enda - begina == 1) {
            if (a[begina] < b[beginb]) {
                return b[beginb] > a[enda] ? a[enda] : b[beginb];
            } else {
                return a[begina] < b[endb] ? a[begina] : b[endb];
            }
        }
        if (endb - beginb < 2) {
            if ((endb - beginb == 0) && (enda - begina + 1) % 2 != 0) {
                // 可能性一：nums1 奇数个，nums2 只有 1 个元素。
                int m = b[beginb];
                int bb = a[(enda + begina) / 2 - 1];
                int c = a[(enda + begina) / 2];
                return (m < bb) ? bb : (m < c ? m : c);
            } else if ((endb - beginb == 0) && (enda - begina + 1) % 2 == 0) {
                // 可能性二：nums1 偶数个，nums2 只有 1 个元素。
                int m = b[beginb];
                int c = a[(enda + begina) / 2];
                int d = a[(enda + begina) / 2 + 1];
                return m < c ? c : (m < d ? m : d);
            } else {
                // 可能性三：nums1 奇数个，nums2 有 2 个元素。
                // 可能性四：nums1 偶数个，nums2 有 2 个元素。
                int m = b[beginb];
                int n = b[endb];
                int bb = a[(enda + begina) / 2 - 1];
                int c = a[(enda + begina) / 2];
                int d = a[(enda + begina) / 2 + 1];
                if (n < bb) {
                    return bb;
                } else if (n > bb && n < c) {
                    return n;
                } else if (n > c && n < d) {
                    return m > c ? m : c;
                } else {
                    return m < c ? c : (m < d ? m : d);
                }
            }
        } else {
            // 二分查找
            int mida = (enda + begina) / 2;
            int midb = (endb + beginb) / 2;
            if (a[mida] < b[midb]) {
                int step = endb - midb;
                return getMedian(a, begina + step, enda, b, beginb, endb - step);
            } else {
                int step = midb - beginb;
                return getMedian(a, begina, enda - step, b, beginb + step, endb);
            }
        }
    }
}
