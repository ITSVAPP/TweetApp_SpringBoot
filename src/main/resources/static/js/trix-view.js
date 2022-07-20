document.querySelectorAll("td[data-url]").forEach(dom => {
	const imgUrl = dom.getAttribute("data-url");
	if (imgUrl) {
		const galleyDom = Array.from(dom.children).find(child => child.classList.contains("attachment-gallery"));
		imgUrl.split(",").forEach(url => {
			const imgElement = document.createElement('img');
			imgElement.src = url;
			imgElement.classList.add("attachment");
			
			if(galleyDom){
				galleyDom.appendChild(imgElement);
			}else{
				dom.appendChild(imgElement);
			}
		})
	}
})