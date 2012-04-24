var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":204,"id":2618,"methods":[{"el":26,"sc":2,"sl":24},{"el":48,"sc":2,"sl":41},{"el":52,"sc":2,"sl":50},{"el":56,"sc":2,"sl":54},{"el":63,"sc":2,"sl":61},{"el":74,"sc":2,"sl":72},{"el":82,"sc":2,"sl":80},{"el":93,"sc":2,"sl":91},{"el":100,"sc":2,"sl":98},{"el":110,"sc":2,"sl":108},{"el":117,"sc":2,"sl":115},{"el":127,"sc":2,"sl":125},{"el":131,"sc":2,"sl":129},{"el":135,"sc":2,"sl":133},{"el":139,"sc":2,"sl":137},{"el":143,"sc":2,"sl":141},{"el":152,"sc":2,"sl":150},{"el":156,"sc":2,"sl":154},{"el":160,"sc":2,"sl":158},{"el":193,"sc":2,"sl":162},{"el":199,"sc":2,"sl":195},{"el":203,"sc":2,"sl":201}],"name":"ITypeInstruction","sl":12}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_0":{"methods":[{"sl":80},{"sl":98},{"sl":115},{"sl":162}],"name":"InstructionTest","pass":true,"statements":[{"sl":81},{"sl":99},{"sl":116},{"sl":163},{"sl":166},{"sl":167},{"sl":168},{"sl":169},{"sl":170},{"sl":171},{"sl":172},{"sl":173},{"sl":174},{"sl":175},{"sl":176},{"sl":177},{"sl":178},{"sl":179},{"sl":180},{"sl":181},{"sl":182},{"sl":183},{"sl":184},{"sl":185},{"sl":187},{"sl":188},{"sl":191}]},"test_10":{"methods":[{"sl":80}],"name":"BasicFetchWorks","pass":true,"statements":[{"sl":81}]},"test_13":{"methods":[{"sl":72},{"sl":91},{"sl":108},{"sl":125},{"sl":162}],"name":"IConstructorsWork","pass":true,"statements":[{"sl":73},{"sl":92},{"sl":109},{"sl":126},{"sl":163},{"sl":166},{"sl":167},{"sl":171},{"sl":172},{"sl":187},{"sl":188},{"sl":191}]},"test_14":{"methods":[{"sl":61},{"sl":80},{"sl":98},{"sl":115}],"name":"BEQTest","pass":true,"statements":[{"sl":62},{"sl":81},{"sl":99},{"sl":116}]},"test_18":{"methods":[{"sl":61}],"name":"LabelTest","pass":true,"statements":[{"sl":62}]},"test_2":{"methods":[{"sl":41},{"sl":61},{"sl":80},{"sl":98},{"sl":115},{"sl":150},{"sl":195}],"name":"EncodeTest","pass":true,"statements":[{"sl":42},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":47},{"sl":62},{"sl":81},{"sl":99},{"sl":116},{"sl":151},{"sl":198}]},"test_3":{"methods":[{"sl":61},{"sl":80},{"sl":115}],"name":"BEQZTest","pass":true,"statements":[{"sl":62},{"sl":81},{"sl":116}]},"test_4":{"methods":[{"sl":41},{"sl":61},{"sl":80},{"sl":98},{"sl":115},{"sl":150},{"sl":195}],"name":"DecodeTest","pass":true,"statements":[{"sl":42},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":47},{"sl":62},{"sl":81},{"sl":99},{"sl":116},{"sl":151},{"sl":198}]},"test_5":{"methods":[{"sl":61},{"sl":80},{"sl":98},{"sl":115}],"name":"BNETest","pass":true,"statements":[{"sl":62},{"sl":81},{"sl":99},{"sl":116}]},"test_9":{"methods":[{"sl":80},{"sl":98},{"sl":115},{"sl":150},{"sl":195}],"name":"issueTest","pass":true,"statements":[{"sl":81},{"sl":99},{"sl":116},{"sl":151},{"sl":198}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [2, 4], [2, 4], [2, 4], [2, 4], [2, 4], [2, 4], [2, 4], [], [], [], [], [], [], [], [], [], [], [], [], [], [18, 3, 2, 14, 5, 4], [18, 3, 2, 14, 5, 4], [], [], [], [], [], [], [], [], [], [13], [13], [], [], [], [], [], [], [3, 2, 14, 10, 5, 9, 4, 0], [3, 2, 14, 10, 5, 9, 4, 0], [], [], [], [], [], [], [], [], [], [13], [13], [], [], [], [], [], [2, 14, 5, 9, 4, 0], [2, 14, 5, 9, 4, 0], [], [], [], [], [], [], [], [], [13], [13], [], [], [], [], [], [3, 2, 14, 5, 9, 4, 0], [3, 2, 14, 5, 9, 4, 0], [], [], [], [], [], [], [], [], [13], [13], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [2, 9, 4], [2, 9, 4], [], [], [], [], [], [], [], [], [], [], [0, 13], [0, 13], [], [], [0, 13], [0, 13], [0], [0], [0], [0, 13], [0, 13], [0], [0], [0], [0], [0], [0], [0], [0], [0], [0], [0], [0], [0], [], [0, 13], [0, 13], [], [], [0, 13], [], [], [], [2, 9, 4], [], [], [2, 9, 4], [], [], [], [], [], []]