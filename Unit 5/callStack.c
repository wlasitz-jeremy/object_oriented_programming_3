void main()
{
	int xInMain = 1, returnFromMethodA;
	// 2 is returned
    returnFromMethodA = methodA(xInMain);
}

int methodA(int xFromMain) // 1 passed in
{
	int aInMethodA = 1;
    methodB();
	// returns
	return aInMethodA + xFromMain;
}

void methodB() 
{
    methodC();
	// returns
}

void methodC()
{
	// whatever
}
