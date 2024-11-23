//
// Coded by Prudence Wong 2021-12-29
//
// NOTE: You are allowed to add additional methods if you need.
//
// Name:
// Student ID:
//
// Time Complexity and explanation: You can use the following variables for easier reference.
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// noEvict():
//
// evictFIFO():
//
// evictLFU():
//
// evictLRU():
//

class COMP108A1Paging {
	private static boolean contains(int[]x, int size, int finding){
		boolean found= false;
		int i;
		for (i =0;i<size;i++){
			if (x[i]==finding){
				found = true;
			}
		}
		return found;
	}






	private static int minumunNumber (int[]array,int sizeOfArray){
		int i,smallest;
		smallest = array[0];
		for (i=0;i<sizeOfArray;i++){
			if (array[i]<smallest)
			smallest =array[i];
		}



		return smallest;


	}



	// no eviction
	// Aim:
	// do not evict any page
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output noEvict(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		for (int i=0; i<rSize; i++){
			boolean searching = contains(cArray,cSize, rArray[i]);
				if (searching == true){
						output.hitCount=output.hitCount+1;
						output.hitPattern+="h";
				}
				else{
					output.missCount=output.missCount+1;
					output.hitPattern+="m";
				}
			}


		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// evict FIFO
	// Aim:
	// if a request is not in cache, evict the page present in cache for longest time
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictFIFO(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int a=0;

		for (int i=0; i<rSize; i++){
			boolean searching = contains(cArray,cSize,rArray[i]);
				if (searching==false ){
					cArray[a]=rArray[i];
					a++;
					if (a>=cSize){
						a=(a-cSize);
					}
						output.missCount=output.missCount+1;
						output.hitPattern+="m";
				}
				else{
					output.hitCount=output.hitCount+1;
					output.hitPattern+="h";

			}
		}

		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// evict LFU
	// Aim:
	// if a request is not in cache, evict the page that is least freqently used since in the cache
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictLFU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int i=0 ;
		int k = 0;



		for (i=0; i<rSize; i++){

			int[] farray = new int[rSize];
			int visited = 1;
			for ( i=0;i<rSize;i++){
				for (int j = i+1;j<rSize;j++){
					if (cArray[i]==rArray[j]){
						visited++;
						farray[j]=visited;

					}
				}
				if (farray[i]!=visited){
					farray[i]=visited;
				}
				farray[k]=rArray[i];

				}
				int min = farray[0];
				for (k=0;k<rSize;k++){
					if (farray[k]<min){
						min=farray[k];
					}
				}

			boolean searching = contains(cArray,cSize,rArray[i]);
			if (searching==true){
				output.hitCount=output.hitCount+1;
				output.hitPattern+="h";
				visited++;

			}


			else{
					min=rArray[i];
					output.missCount=output.missCount+1;
					output.hitPattern+="m";
				}
			}
			output.cache = arrayToString(cArray, cSize);
			return output;
		}



	// evict LRU
	// Aim:
	// if a request is not in cache, evict the page that hasn't been used for the longest amount of time
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictLRU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();

		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// DO NOT change this method
	// this will turn the cache into a String
	// Only to be used for output, do not use it to manipulate the cache
	static String arrayToString(int[] array, int size) {
		String outString="";

		for (int i=0; i<size; i++) {
			outString += array[i];
			outString += ",";
		}
		return outString;
}
}
