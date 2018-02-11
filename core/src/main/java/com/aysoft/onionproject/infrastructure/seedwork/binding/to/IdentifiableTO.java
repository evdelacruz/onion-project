package com.aysoft.onionproject.infrastructure.seedwork.binding.to;

import java.io.Serializable;

public interface IdentifiableTO<T extends Serializable> {

    T getId();
}
