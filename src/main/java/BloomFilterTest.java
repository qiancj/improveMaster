import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {

    private static final int TOTAL_NUM = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), TOTAL_NUM, 0.0003);

    private static void testBloomFilter() {
        for (int i = 0; i < TOTAL_NUM; ++i) {
            bloomFilter.put(i);
        }

        for (int i = 0; i < TOTAL_NUM; ++i) {
            if (!bloomFilter.mightContain(i)) {
                System.out.println("判断错误的数值：" + i);
            }
        }

        int count = 0;
        for (int i = TOTAL_NUM; i < TOTAL_NUM + 100000; ++i) {
            if (bloomFilter.mightContain(i)) {
                count++;
            }
        }
        System.out.println("判断错误的数量2：" + count);
    }

    public static void main(String[] args) {
        testBloomFilter();
    }
}
