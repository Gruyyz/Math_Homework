clear;
[X,Y] = dsolve('2*Dx+4*x+Dy-y=exp(t),Dx+3*Dx+y=0','x(0)=3/2','y(0)=0');
pretty(X)
pretty(Y)
display(X)
display(Y)