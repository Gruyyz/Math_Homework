clear;
M(1) = 10^8;
i=1;
while M(i)>10^5
    i=i+1;
    M(i)=0.2*M(i-1)*exp(3*log(10)/100*11);
end
disp(i-1)
disp(M(i))