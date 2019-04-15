package com.example.aras1.calculator;


public class Status
    {
        private Double resultNumber;

        private OperationType lastOperation = OperationType.NULL;
        private boolean isDotInResult = false;
        private boolean isNegativeNumber = false;

        public Double getResultNumber() {
            return resultNumber;
        }

        public void setResultNumber(Double resultNumber) {
            this.resultNumber = resultNumber;
        }

        public OperationType getLastOperation() {
            return lastOperation;
        }

        public void setLastOperation(OperationType lastOperation) {
            this.lastOperation = lastOperation;
        }

        public boolean isDotInResult() {
            return isDotInResult;
        }

        public void setDotInResult(boolean dotInResult) {
            isDotInResult = dotInResult;
        }

        public boolean isNegativeNumber() {
            return isNegativeNumber;
        }

        public void setNegativeNumber(boolean negativeNumber) {
            isNegativeNumber = negativeNumber;
        }
    }
