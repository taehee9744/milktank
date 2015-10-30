package control;

import java.io.File;
//지원하지 않는 인코딩일때 발생하는 예외 
import java.io.UnsupportedEncodingException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.BoardVO;

public class PicUpload {
	private HttpServletRequest request;

	public PicUpload(HttpServletRequest request) {
		this.request = request;
	}

	public BoardVO upload() {
		BoardVO vo = new BoardVO();
		// Post(어플리케이션 방식과 멀티파트 방식)
		// 파일 업로드는 멀티파트 방식
		if (ServletFileUpload.isMultipartContent(request)) {
			//System.out.println(request);
			// 파일 업로드된 파일을 처리하기 위해서 필요한 객체
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);

			List<FileItem> fileItemList = null;
			try {
				// 전송된 파일과 텍스트를 저장(fileItemList)
				fileItemList = fileUpload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < fileItemList.size(); i++) {
				//System.out.println(fileItemList);
				FileItem fileItem = (FileItem) fileItemList.get(i);
				// isFormField()는 텍스인지 판단
				//System.out.println(fileItem);
				if (fileItem.isFormField()) {
					// 양식의 이름 뽑아서 저장
					String name = fileItem.getFieldName();
					String value = null;
					try {
						value = fileItem.getString("euc-kr");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (name.equals("title")) {
						vo.setTitle(value);
						System.out.println(vo.getTitle());
					} else if (name.equals("content")) {
						vo.setContent(value);
					} else if (name.equals("section")) {
						vo.setSection(Integer.parseInt(value));
					} else if (name.equals("magazine")) {
						vo.setMagazine(Integer.parseInt(value));
					}

				} else {
					String name = fileItem.getFieldName();
					// 마지막 역슬레쉬의 위치 (파일명만 짤라오고)
					int idx = fileItem.getName().lastIndexOf("\\");
					// 만약에 파일(마지막 슬레쉬의 위치를 찾거나 )
					if (idx == -1) {
						idx = fileItem.getName().lastIndexOf("/");
					}
					// 파일명만 짤라오고
					String fileName = fileItem.getName().substring(idx + 1);
					try {

						File uploadedFile = new File("C:\\Users\\bit\\Desktop\\javaWork\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\webapps\\img", fileName);
						
						fileItem.write(uploadedFile);
						vo.setPath(uploadedFile.getAbsolutePath());

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		return vo;

	}
}
