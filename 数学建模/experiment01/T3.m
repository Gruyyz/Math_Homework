    clear;
    syms t
    L = dsolve('Dl=-2*k','l(0)=1','t');
    display(L);