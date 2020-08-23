package ru.progwards.java1.lessons.classes;

public class ComplexNum {
int real, imagine;



    public ComplexNum(int a, int b){//3.1
this.real = a;
this.imagine = b;


    }

    public static void main(String[] args) {
        System.out.println(new ComplexNum(12,13).add(new ComplexNum(5,7)).toString());
        System.out.println(new ComplexNum(12,13).sub(new ComplexNum(5,7)).toString());
        System.out.println(new ComplexNum(12,13).mul(new ComplexNum(5,7)).toString());
        System.out.println(new ComplexNum(12,13).div(new ComplexNum(5,7)).toString());

    }
    public String toString() {
        return real + "+" + imagine + "i";
    }//3.2

    public ComplexNum add(ComplexNum num){//3.3  (a + bi) + (c + di) = (a + c) + (b + d)i
        int sumReal  =  (this.real + num.real);//(a + c)
             int sumImagine  = (this.imagine + num.imagine);//(b + d)i
             ComplexNum sumComplex = new ComplexNum(sumReal, sumImagine);
             return sumComplex;
    }

    public ComplexNum sub(ComplexNum num){//3.4 (a + bi) - (c + di) = (a - c) + (b - d)i
        int substractionReal  =  (this.real - num.real);//(a - c)
        int substractionImagine  = (this.imagine - num.imagine);//(b - d)i
        ComplexNum substractionComplex = new ComplexNum(substractionReal, substractionImagine);
        return substractionComplex;
    }

    public ComplexNum mul(ComplexNum num){//3.5 (a + bi) * (c + di) = (a*c - b*d) + (b*c + a*d)i
        int mulReal = this.real * num.real - this.imagine * num.imagine;//(a*c - b*d)
        int mulImagine = this.imagine * num.real + this.real * num.imagine;//(b*c + a*d)i
        ComplexNum mulComplex = new ComplexNum(mulReal, mulImagine);
        return mulComplex;
    }


    public ComplexNum div(ComplexNum num){//3.6 (a + bi) / (c + di) = (a*c + b*d)/(c*c+d*d) + ((b*c - a*d)/(c*c+d*d))i
int firstNumerator = this.real * num.real + this.imagine * num.imagine;//(a*c + b*d)
        int CommonDenominator = num.real * num.real + num.imagine * num.imagine;//(c*c+d*d)
        int secondNumerator = this.imagine * num.real - this.real * num.imagine;//(b*c - a*d)
        int divReal = firstNumerator / CommonDenominator;// (a*c + b*d)/(c*c+d*d)
        int divImagine = secondNumerator / CommonDenominator; //(b*c - a*d)/(c*c+d*d)
        ComplexNum divComplex = new ComplexNum(divReal, divImagine);
        return divComplex;
    }
}
