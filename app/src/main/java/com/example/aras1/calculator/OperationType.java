package com.example.aras1.calculator;

public enum OperationType
    {
        NULL((x, y) -> 0D),

        ADD((x, y) -> x + y),

        SUBTRACT((x, y) -> x - y),

        MULTIPLY((x, y) -> x * y),

        DIVIDE((x, y) -> x / y),

        RESULT((x, y) -> null),

        POW2((x, y) ->
        {
        return Math.pow(x, 2);
        }, true),

        POWX((x, y) ->
        {
        return Math.pow(x, y);
        }),

        LN((x, y) ->
        {
        return Math.log(x);
        }, true),

        SQRTY((x, y) ->
        {
        return Math.pow(x, 1 / y);
        }),

        SIN((x, y) ->
        {
        return Math.sin(x);
        }, true),
        COS((x, y) ->
        {
        return Math.cos(x);
        }, true),
        TG((x, y) ->
        {
        return Math.tan(x);
        }, true),
        LOG((x, y) ->
        {
        return Math.log10(x);
        }, true),
        PER((x, y) -> x *(y/100D));

    private Calculate calculate;
    private Boolean isOneArg;

    OperationType(Calculate calculate)
        {
        this(calculate, false);
        }

    OperationType(Calculate calculate, Boolean isOneArg)
        {
        this.calculate = calculate;
        this.isOneArg = isOneArg;
        }

    Double getResultFormOperation(Double x, Double y)
        {
        return calculate.calculate(x, y);
        }

    public Boolean isOneArg()
        {
        return isOneArg;
        }
    }
