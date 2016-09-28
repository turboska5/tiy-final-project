package com.andrewrnagel.service;

import com.andrewrnagel.repository.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:50 PM EST.
 */

@Service
public class MainService {
    //object properties
    @Autowired
    private ClassRepo classRepo;
}