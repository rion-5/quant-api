package com.rion5.quantapi.dto.tradedatachart;

import java.util.List;

public record Multiple(String name, List<Series> series){}
