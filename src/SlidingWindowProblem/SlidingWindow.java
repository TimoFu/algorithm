Sliding window problem we can have a template to solve it;

int j = 0;
for (int i = 0; i < n; i ++){
	while (j < n){
		if (meet some case){
			j ++;
			//更新j状态
		}else{
			break;
		}
	}
}


