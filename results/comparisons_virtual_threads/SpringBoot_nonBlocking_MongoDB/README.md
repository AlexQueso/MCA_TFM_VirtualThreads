# Virtual Threads usage Comparison: Spring Boot - Non blocking programming approach - MongoDB 

## Response times analysis
The results regarding response times (expressed in milliseconds) are shown in the following graph:

![](../../assets/graphs/sb-react-mongo-1.png)

Similarly to the [Spring boot  blocking -mongoDB comparison](../SpringBoot_Blocking_MongoDB/), the time response results are almost identical.

## Resources management analysis

|   | Without Virtual Threads | With Virtual Threads |
|---|---|---|
| CPU | ![](../../assets/load_testing_reports/04_reactive-sb-mongo-novt/cpu.png) | ![](../../assets/load_testing_reports/12_reactive-sb-mongo-vt/cpu.png) |
| JVM Heap | ![](../../assets/load_testing_reports/04_reactive-sb-mongo-novt/heap.png) | ![](../../assets/load_testing_reports/12_reactive-sb-mongo-vt/heap.png) |
| Threads usage | ![](../../assets/load_testing_reports/04_reactive-sb-mongo-novt/threads.png) | ![](../../assets/load_testing_reports/12_reactive-sb-mongo-vt/threads.png) |

Regarding resource efficiency, the version using virtual threads slightly improves CPU usage efficiency, while the JVM memory usage percentage and thread management are very similar.

## Conclusion

It can be concluded that performance is slightly better when execution is done using virtual threads, but the improvement is practically imperceptible from the user's perspective.

