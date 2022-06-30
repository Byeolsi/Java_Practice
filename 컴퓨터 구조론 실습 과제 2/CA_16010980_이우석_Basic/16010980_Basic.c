#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include <dos.h>
#include <conio.h>

#define blockSize 32
#define blockOffset 3

//16���� 2���� ��ȯ�Լ� ����.
// void Convert16to2(char *Hex_array);



FILE *fp;
char *Hex_array; // ���ϼ� �о�� 16������ ���ڿ��� �б� ���� ������ ����
unsigned int fetched_inst; //��ɾ��� 16������ ���������� �б� ���� ����

////////////////////////////////////////////////////////////////
int operand; // (0�϶� read data, 1 write data)  <- data cache , 2 instruction fetch <- instruction cache  
char binary[33];//16 -> 2������ ��ȯ ���� ����
///////////////////////////////////////////////////////////////

int main()
{
	char str_file[20] = { 0 };
	int i, cacheSize, blockN, indexSize = 0, tagSize, access = 0, miss = 0, tmp;
	unsigned int tag, index, *cache;
	double missRatio;
	int *validBit;

	// Trace file �Է� �ޱ�
	printf("File : ");
	scanf("%s", str_file);

	//  Ʈ���̽� ���� �б�
	if((fp=fopen(str_file, "r")) == NULL) {		
		printf("file open error");
		exit(1);
	}

	printf("Unified cache size (KB) : ");
	scanf("%d", &cacheSize);

	blockN = cacheSize * pow((double)2, (double)10) / blockSize;

	tmp = blockN;
	while (tmp != 1){
		tmp /= 2;
		indexSize++;
	}
	tagSize = 32 - indexSize - 2;

	cache = (unsigned int *)malloc(blockN * sizeof(unsigned int));

	validBit = (int *)malloc(blockN * sizeof(int));
	for (i = 0 ; i < blockN ; i++){
		validBit[i] = 0;
	}
	
	Hex_array = (char *)malloc(sizeof(char) * 11);
	
	while(feof(fp)==0)		//���� ������ ���ڿ� �ҷ�����.
	{
		fscanf(fp, "%d", &operand);
		// fscanf(fp, "%s", Hex_array); //��ɾ ���ڿ��� ����
		fscanf(fp, "%x", &fetched_inst);
		/* Trace file ��ɾ ���������� �б� - ��ɾ ���ڿ��� �д� �Լ��� ��ü�� Ȱ�� ����
		fscanf(fp, "%x", &fetched_inst);
		*/
		// Convert16to2(Hex_array); //16���� ���ڿ��� 2���� ������ ���ڿ��� ����
		//���������� ��ɾ �о��ٸ� Convert16to2 �Լ��� Ȱ���� �ʿ� ����
		/*
		tag = (fetched_inst>>1);
		tag &= (0x7fffffff);
		tag = (tag>>(indexSize+2-1));
		*/
		tag = (fetched_inst>>(indexSize+2));
		index = (fetched_inst<<tagSize);
		index = (index>>tagSize+2);
		//������ ��ɾ��� Ư�� bit �ڸ��� ���� (tag 22bit, index 8bit, direct-mapped cache)
		//index = (fetched_inst<<22);
		//index = (index>>24); -> index�� �ش��ϴ� ���� �����

		if (validBit[index] == 0){
			cache[index] = tag;
			validBit[index] = 1;
			access++;
			miss++;
		}
		else {
			if (cache[index] != tag){
				cache[index] = tag;
				miss++;
			}
			access++;
		}
		
		/*
		if(operand == 0 || operand == 1)		//data cahce ���� ����
		{
			printf("data %d    %x\n",operand, fetched_inst);
		}
		if(operand == 2)				//inst cache ���� ����
		{
			printf("inst %d    %x\n",operand, fetched_inst);
		}
		*/
	}
	missRatio = (double)miss / access;

	printf("Access : %d\n", access);
	printf("Miss : %d\n", miss);
	printf("Miss ratio : %lf\n", missRatio);
	
	return 0;
}


//16���� 2���� ��ȯ�Լ�.
/*
void Convert16to2(char *Hex_array)
{
	int res=0;
	char temp;
	unsigned long Hex = strtoul(Hex_array, NULL, 16);

	for (int i = 0; i < 32; i++)
	{
		res = Hex & (0x01 << (31 - i));
		if (res == 0)
		{
			temp = '0';
			binary[i] = temp;
		}
		else
		{
			temp = '1';
			binary[i] = temp;
		}
	}
}
*/