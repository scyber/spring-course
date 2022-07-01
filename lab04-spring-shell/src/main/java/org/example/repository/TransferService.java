package org.example.repository;


import java.util.Map;

public interface TransferService <T> {
    Map<Long,T> transfer();
}
